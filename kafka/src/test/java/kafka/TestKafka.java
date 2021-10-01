package kafka;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;

import kafka.config.AppPropertiesConfig;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

@SpringBootTest
@EmbeddedKafka(bootstrapServersProperty = "spring.kafka.bootstrap-servers")
public class TestKafka {

	@Autowired
	private AppPropertiesConfig config;

	@Test
	void testDistinct() {

		String inputTopic = "erkan-avro-test";
		String outputTopic = "erkan-avro-test";
		try (
				Producer<String, String> producer = configureProducer();
				Consumer<String, String> consumer = configureConsumer(outputTopic );
				) {
			Stream.of("A", "B", "B", "A").map(
					x -> new ProducerRecord(inputTopic, x, x)
					).forEach(rec -> producer.send(rec));
			producer.flush();

			List<String> values = new ArrayList<>();
			ConsumerRecords<String, String> records;
			while (!(records = consumer.poll(Duration.ofSeconds(5))).isEmpty()) {
				for (ConsumerRecord<String, String> rec : records) {
					values.add(rec.value());
				}
			}
			Assertions.assertEquals(List.of("A", "B"), values);
		}
	}
	
	protected void testWithAwaitility(String inputTopicName, String outputTopicName) throws InterruptedException {
        try (Consumer<String, String> consumer = configureConsumer(outputTopicName);
             Producer<String, String> producer = configureProducer()) {

            Stream.of("A", "B", "B", "A")
                    .map(e -> new ProducerRecord<>(inputTopicName, e, e))
                    .forEach(producer::send);
            producer.flush();

            //We are using thread-safe data structure here, since it's shared between consumer and verifier
            List<String> actual = new CopyOnWriteArrayList<>();
            ExecutorService service = Executors.newSingleThreadExecutor();
            Future<?> consumingTask = service.submit(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                    for (ConsumerRecord<String, String> rec : records) {
                        actual.add(rec.value());
                    }
                }
            });

            try {
                Awaitility.await().atMost(5, SECONDS)
                        .until(() -> List.of("A", "B").equals(actual));
            } finally {
                consumingTask.cancel(true);
                service.awaitTermination(200, MILLISECONDS);
            }
        }
    }

	private Producer<String, String> configureProducer() {
		Map<String, Object> producerProps = new HashMap<>(KafkaTestUtils.producerProps(
				String.join(",", config.getBootstrapservices())));
		return new DefaultKafkaProducerFactory<>(producerProps,
				new StringSerializer(), new StringSerializer()).createProducer();
	}
    private Consumer<String, String> configureConsumer(String outputTopicName) {
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps(
                String.join(",", config.getBootstrapservices()), "testGroup", "true");
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        Consumer<String, String> consumer = new DefaultKafkaConsumerFactory<>(consumerProps,
                new StringDeserializer(), new StringDeserializer())
                .createConsumer();
        consumer.subscribe(Collections.singleton(outputTopicName));
        return consumer;
    }

}
