package examples.alexander;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = "controllers")
@ComponentScan(basePackages = "repositories")
@EnableScheduling
public class SubscriptionListApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubscriptionListApplication.class, args);
	}

}
