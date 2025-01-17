package sg.edu.nus.iss.paf_day25_wsA.service;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import sg.edu.nus.iss.paf_day25_wsA.model.Order;
import sg.edu.nus.iss.paf_day25_wsA.util.OrderUtil;

@Component
public class MessagePoller {
    
    @Autowired
    @Qualifier("order")
    RedisTemplate<String, String> template;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderUtil orderUtil;

    @Async
    public void start() {
        
        Runnable poller = () -> {
            ListOperations<String, String> orderList = template.opsForList();
            while (true) {
                Optional<String> opt = Optional.ofNullable(
                    orderList.rightPop("default", Duration.ofSeconds(5)));
                if (opt.isPresent()) {
                    String data = opt.get();
                    Order order = orderUtil.jsonToPojo(data);
                    orderService.saveOrderToDatabase(order);
                }
            }
        };

        Executors.newSingleThreadExecutor().execute(poller);
    }
}
