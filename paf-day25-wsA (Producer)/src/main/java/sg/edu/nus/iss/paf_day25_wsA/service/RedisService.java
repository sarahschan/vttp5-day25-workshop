package sg.edu.nus.iss.paf_day25_wsA.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.paf_day25_wsA.repo.RedisRepo;

@Service
public class RedisService {
    
    @Autowired
    RedisRepo redisRepo;

    @Autowired
    ConfigurableEnvironment environment;

    public List<String> retrieveAppNames(String key) {
        List<String> customerNames = redisRepo.getList(key);
        return new ArrayList<>(customerNames);
    }


    public void saveOrderToRedis(String order) {
        String customerName = environment.getProperty("customer.name");
        redisRepo.saveKeyAndValue(customerName, order);
        System.out.println("Saved to redis - Order from customer " + customerName);
    }

}
