package example.redis.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;

import example.redis.dto.EventDto;

@Component
public class EventConsumer implements StreamListener<String, ObjectRecord<String, EventDto>> {

	@Value("${app.stream.key}")
	private String streamKey;

	private static final Logger log = LoggerFactory.getLogger(EventConsumer.class);

	private final RedisTemplate<String, String> redisTemplate;

	public EventConsumer(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public void onMessage(ObjectRecord<String, EventDto> message) {
		EventDto event = message.getValue();
		log.warn("onMessage: consumed: id={}, value={}", message.getId(), event.getText());
		redisTemplate.opsForStream().acknowledge(streamKey, streamKey, message.getId());
	}
}
