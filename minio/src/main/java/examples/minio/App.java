package examples.minio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import examples.minio.util.Utils;

@SpringBootApplication
public class App implements CommandLineRunner{
	
	@Autowired
	Utils utils;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);	
	}

	@Override
	public void run(String... args) throws Exception {
		utils.checkBucket();		
	}

}
