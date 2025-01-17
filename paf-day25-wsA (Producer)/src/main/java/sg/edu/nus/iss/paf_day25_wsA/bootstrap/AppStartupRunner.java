package sg.edu.nus.iss.paf_day25_wsA.bootstrap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


@Component
public class AppStartupRunner implements CommandLineRunner {

    @Autowired
    private ConfigurableEnvironment environment;

    @Autowired
    @Qualifier("order")
    RedisTemplate<String, String> redisTemplate;

    private String defaultName = "default";


    @Override
    public void run(String... args) throws Exception {
        
        if (args.length > 0) {
            environment.getSystemProperties().put("customer.name", args[0]);

        } else {
            System.out.println("No customer name provided, using default");
            environment.getSystemProperties().put("customer.name", defaultName);
        }

        String finalCustomerName = environment.getProperty("customer.name");
        System.out.println("Customer name set to: " + finalCustomerName);


        List<String> registrations = redisTemplate.opsForList().range("registrations", 0, -1);

        if (registrations == null || !registrations.contains(finalCustomerName)) {
            redisTemplate.opsForList().rightPush("registrations", finalCustomerName);
            System.out.printf("Customer %s saved to Redis list 'registrations'", finalCustomerName);

        } else {
            System.out.printf("Customer %s already existing in Redis list 'registrations'", finalCustomerName);

        }
    }

}
