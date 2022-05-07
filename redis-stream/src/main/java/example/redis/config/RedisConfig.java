package example.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.ObjectRecord;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;

import example.redis.dto.EventDto;

import java.time.Duration;

@Configuration
public class RedisConfig {

	private final StreamListener<String, ObjectRecord<String, EventDto>> streamListener;
	
	@Value("${app.stream.key}")
	private String streamKey;

	public RedisConfig(StreamListener<String, ObjectRecord<String, EventDto>> streamListener) {
		this.streamListener = streamListener;
	}

	@Bean
	Subscription subscription(RedisConnectionFactory factory) {
		var options = StreamMessageListenerContainer
				.StreamMessageListenerContainerOptions
				.builder()
				.pollTimeout(Duration.ofMillis(200))
				.targetType(EventDto.class)
				.build();

		var listenerContainer = StreamMessageListenerContainer
				.create(factory, options);

		var subscription = listenerContainer
				.receive(Consumer.from(streamKey, "event-app-consumer"),
						StreamOffset.create(streamKey, ReadOffset.lastConsumed()), streamListener);

		listenerContainer.start();

		return subscription;
	}

}
