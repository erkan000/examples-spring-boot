package helloworld.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Bean
	public void getDatasource() {
		// we can add desired custom configuration to spring context.
	}

}
