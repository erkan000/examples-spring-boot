package kafka.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka-global")
public class AppPropertiesConfig {
	
	private String bootstrapservices;
	private String groupId;
	
	public String getBootstrapservices() {
		return bootstrapservices;
	}
	public void setBootstrapservices(String bootstrapservices) {
		this.bootstrapservices = bootstrapservices;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}


}
