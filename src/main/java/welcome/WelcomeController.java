package welcome;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class WelcomeController {

    private final static String template = "Welcome to Skip, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/welcome")
    public Welcome welcome(@RequestParam(value = "name", defaultValue = "Customer") String name){
        return new Welcome(counter.incrementAndGet(),
                String.format(template, name));
    }

}
