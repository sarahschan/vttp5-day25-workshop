package sg.edu.nus.iss.paf_day25_wsA.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    
    @Autowired
    @Qualifier("registrations")
    RedisTemplate<String, String> redisTemplate;

    @Value("${customer.name}")
    private String customerName;


    public void sendOrder(String orderJsonString){
        redisTemplate.convertAndSend(customerName, orderJsonString);
    }


    public List<String> retrieveAppNames() {
        List<String> customerNames = redisTemplate.opsForList().range("registrations", 0, -1);
        return new ArrayList<>(customerNames);
    }


    public void saveOrderToRedis(String order) {
        redisTemplate.opsForList().rightPush(customerName, order);
    }

}
