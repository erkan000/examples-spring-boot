package tr.examples.processor;

import java.util.function.Function;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Processor1 {	

	@Bean
	public Function<KStream<String, String>, KStream<String, String>> processEvent1() {
		return kstream -> kstream.map(convertEvent());
	}

	private KeyValueMapper<String, String, KeyValue<? extends String, ? extends String>>
			convertEvent() {
		return (String key, String value) -> {
			return new KeyValue<>(key, value + " appended text");
		};
	}


}
