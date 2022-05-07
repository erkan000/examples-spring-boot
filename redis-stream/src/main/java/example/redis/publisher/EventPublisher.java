package example.redis.publisher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.stream.StreamRecords;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import example.redis.dto.EventDto;

@Service
public class EventPublisher {

	@Value("${app.stream.key}")
	private String streamKey;

	private final RedisTemplate<String, String> redisTemplate;

	public EventPublisher(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public void publishEvent(String text, int id) {
		var record = StreamRecords.newRecord()
				.ofObject(new EventDto(id, text))
				.withStreamKey(streamKey);

		redisTemplate.opsForStream().add(record);
	}


}
