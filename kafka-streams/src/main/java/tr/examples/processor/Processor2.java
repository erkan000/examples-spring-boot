package tr.examples.processor;

import java.util.function.Function;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Named;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Processor2 {	

	@Bean
	public Function<KStream<String, String>, KTable<String, String>> processEvent2() {
		return stream -> stream.toTable(
				Named.as("processor2-view"),
				Materialized.<String, String, KeyValueStore<Bytes, byte[]>>as("processor2-view-store")
						.withKeySerde(Serdes.String())
						.withValueSerde(Serdes.String()));
	}

}
