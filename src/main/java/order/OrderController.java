package order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class OrderController {

    public static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderService; //Service which will do all data retrieval/manipulation work

    @RequestMapping("/")
    public String index() {
        return "Welcome to Skip the Dishes!";
    }

    // -------------------Retrieve All Orders---------------------------------------------

    @RequestMapping(value = "/order/", method = GET)
    public ResponseEntity<List<Order>> listAllOrders() {
        List<Order> orders = orderService.findAllOrders();
        if (orders.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
    }

    // -------------------Retrieve Single Order------------------------------------------

    @RequestMapping(value = "/order/{id}", method = GET)
    public ResponseEntity<?> getOrder(@PathVariable("id") long id) {
        logger.info("Fetching Order with id {}", id);
        Order order = orderService.findById(id);
        if (order == null) {
            logger.error("Order with id {} not found.", id);
            return new ResponseEntity(new OrderErrorType("Order with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    // -------------------Create a Order-------------------------------------------

    @RequestMapping(value = "/order/", method = POST)
    public ResponseEntity<?> createOrder(@RequestBody Order order, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Order : {}", order);

        orderService.saveOrder(order);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/order/{id}").buildAndExpand(order.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update Order ------------------------------------------------

    @RequestMapping(value = "/order/{id}", method = PUT)
    public ResponseEntity<?> updateOrder(@PathVariable("id") long id, @RequestBody Order order) {
        logger.info("Updating Order with id {}", id);

        Order currentOrder = orderService.findById(id);

        if (currentOrder == null) {
            logger.error("Unable to update. Order with id {} not found.", id);
            return new ResponseEntity(new OrderErrorType("Unable to upate. Order with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentOrder.setItemOrders(order.getItemOrders());
        currentOrder.setOrderDone(order.isOrderDone());

        orderService.updateOrder(currentOrder);
        return new ResponseEntity<Order>(currentOrder, HttpStatus.OK);
    }

    // ------------------- Delete a Order-----------------------------------------

    @RequestMapping(value = "/order/{id}", method = DELETE)
    public ResponseEntity<?> deleteOrder(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting Order with id {}", id);

        Order order = orderService.findById(id);
        if (order == null) {
            logger.error("Unable to delete. Order with id {} not found.", id);
            return new ResponseEntity(new OrderErrorType("Unable to delete. Order with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        orderService.deleteOrderById(id);
        return new ResponseEntity<Order>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Orders-----------------------------

    @RequestMapping(value = "/order/", method = DELETE)
    public ResponseEntity<Order> deleteAllOrders() {
        logger.info("Deleting All Orders");

        orderService.deleteAllOrders();
        return new ResponseEntity<Order>(HttpStatus.NO_CONTENT);
    }

}
