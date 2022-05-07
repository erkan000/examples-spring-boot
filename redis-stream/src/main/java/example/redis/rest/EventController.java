package example.redis.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import example.redis.publisher.EventPublisher;

@RestController
public class EventController {
	
	@Autowired
	private EventPublisher publisher;
	
	@GetMapping("send/{text}")
	@ResponseBody
	public String sendEvent(@PathVariable String text) {
		publisher.publishEvent(text, 3);
		return "Sent: " + text;
	}

}
