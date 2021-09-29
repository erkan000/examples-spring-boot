package kafka.rest;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kafka.avro.CarModel;

import javax.annotation.PreDestroy;

@RestController
@RequestMapping("/api-avro")
public class ProducerAvroRestService {	
	
	@Autowired
	private KafkaTemplate<Integer, CarModel> kafkaTemplateAvro;

    @GetMapping
    @ResponseBody
    public String getStatus() {
    	
    	CarModel message = new CarModel("Nissan", "Primera");
    	String topic = "erkan-avro-test";
    	
        ListenableFuture<SendResult<Integer, CarModel>> kafkaResultFuture = kafkaTemplateAvro.send(topic, 111, message);
        LOG.info("Message sent async");

        kafkaResultFuture.addCallback(new ListenableFutureCallback<>() {
        	
            @Override
            public void onFailure(Throwable throwable) {
                LOG.error("Error sending ", throwable);
            }

            @Override
            public void onSuccess(SendResult<Integer, CarModel> result) {
                    RecordMetadata metadata = result.getRecordMetadata();
                    LOG.info("Partition {}, Offset {}.", metadata.partition(), metadata.offset());
            }

        });
        
        return "Sent to topic: " + topic;
    }
    
    @PreDestroy
    public void close() {
        if (kafkaTemplateAvro != null) {
            LOG.info("Close producer!");
            kafkaTemplateAvro.destroy();
        }
    }

    private static final Logger LOG = LoggerFactory.getLogger(ProducerAvroRestService.class);
}