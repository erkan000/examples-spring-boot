package kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import kafka.config.AppPropertiesConfig;

@SpringBootApplication
public class Application implements CommandLineRunner{
	
	@Autowired
	private AppPropertiesConfig config;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);	
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Started!");
		System.out.println(config.getBootstrapservices());
		
	}

}
