package sg.edu.nus.iss.paf_day25_wsA.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisRepo {
    
    @Autowired
    @Qualifier("order")
    RedisTemplate<String, String> redisTemplate;

    @Value("${customer.name}")
    private String customerName;


    public void sendOrder(String orderJsonString) {
        redisTemplate.convertAndSend(customerName, orderJsonString);
    }


    public List<String> getList(String key) {
        List<String> list = redisTemplate.opsForList().range(key, 0, -1);
        return list;
    }


    public void saveKeyAndValue(String key, String value) {
        redisTemplate.opsForList().rightPush(key, value);
    }



}
