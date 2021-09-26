package kafka.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CosumerService {
	
	@KafkaListener(topics = "testTopic", groupId = "foo")
	public void listenGroupFoo(String message) {
	    LOG.info("Received Message in group foo: " + message);
	}

	private static final Logger LOG = LoggerFactory.getLogger(ProducerRestService.class);
}
