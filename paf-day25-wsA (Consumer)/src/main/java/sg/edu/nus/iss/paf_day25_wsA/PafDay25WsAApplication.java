package sg.edu.nus.iss.paf_day25_wsA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import sg.edu.nus.iss.paf_day25_wsA.service.MessagePoller;

@SpringBootApplication
@EnableAsync
public class PafDay25WsAApplication implements CommandLineRunner {

	@Autowired
	private MessagePoller poller;

	
	public static void main(String[] args) {
		SpringApplication.run(PafDay25WsAApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		poller.start();
	}

}
