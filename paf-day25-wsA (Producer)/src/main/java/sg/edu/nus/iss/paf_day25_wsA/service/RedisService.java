package sg.edu.nus.iss.paf_day25_wsA.service;

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

    @Value("${app.name}")
    private String appName;


    public void sendOrder(String orderJsonString){
        redisTemplate.convertAndSend(appName, orderJsonString);
    }

    
}
