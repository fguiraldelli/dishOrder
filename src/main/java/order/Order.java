package order;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    private Long id;
    private List<ItemOrder> itemOrders;
    private BigDecimal totalOrderCost;
    private boolean isOrderDone;

    public Order(Long id, List<ItemOrder> itemOrders, BigDecimal totalOrderCost, boolean isOrderDone) {
        this.id = id;
        this.itemOrders = itemOrders;
        this.totalOrderCost = totalOrderCost;
        this.isOrderDone = isOrderDone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItemOrder> getItemOrders() {
        return itemOrders;
    }

    public void setItemOrders(List<ItemOrder> itemOrders) {
        this.itemOrders = itemOrders;
    }

    public BigDecimal getTotalOrderCost() {
        return totalOrderCost;
    }

    public void setTotalOrderCost(BigDecimal totalOrderCost) {
        this.totalOrderCost = totalOrderCost;
    }

    public boolean isOrderDone() {
        return isOrderDone;
    }

    public void setOrderDone(boolean orderDone) {
        isOrderDone = orderDone;
    }
}
