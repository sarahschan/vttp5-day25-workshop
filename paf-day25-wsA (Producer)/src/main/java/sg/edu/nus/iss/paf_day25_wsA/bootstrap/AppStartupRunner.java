package sg.edu.nus.iss.paf_day25_wsA.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


@Component
public class AppStartupRunner implements CommandLineRunner {

    @Autowired
    private ConfigurableEnvironment environment;

    @Autowired
    @Qualifier("registrations")
    RedisTemplate<String, String> redisTemplate;

    private String defaultName = "default";


    @Override
    public void run(String... args) throws Exception {
        
        if (args.length > 0) {
            environment.getSystemProperties().put("app.name", args[0]);

        } else {
            System.out.println("No application name provided, using default");
            environment.getSystemProperties().put("app.name", defaultName);
        }

        String finalAppName = environment.getProperty("app.name");
        System.out.println("Application name set to: " + finalAppName);


        redisTemplate.opsForSet().add("registrations", finalAppName);
        System.out.println("Added application name to registrations list in Redis.");
    }

}
