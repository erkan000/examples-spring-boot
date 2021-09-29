package kafka.config;

import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import io.confluent.kafka.serializers.KafkaAvroSerializer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaAvroProducerConfig<K extends Serializable, V extends SpecificRecordBase> {
	
	@Autowired
	private AppPropertiesConfig config;

    @Bean
    public Map<String, Object> producerConfigAvro() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,  config.getBootstrapservices());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        props.put("schema.registry.url", "http://kafka2test");
        
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 5);
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 1000);
        props.put(ProducerConfig.RETRIES_CONFIG, 3);
        
        
        return props;
    }

    @Bean
    public ProducerFactory<K, V> producerFactoryAvro() {
        return new DefaultKafkaProducerFactory<>(producerConfigAvro());
    }

    @Bean
    public KafkaTemplate<K, V> kafkaTemplateAvro() {
        return new KafkaTemplate<>(producerFactoryAvro());
    }
}
