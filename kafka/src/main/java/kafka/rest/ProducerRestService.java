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

import java.time.LocalTime;

import javax.annotation.PreDestroy;

@RestController
@RequestMapping("/api")
public class ProducerRestService {	
	
	@Autowired
	private KafkaTemplate<Integer, String> kafkaTemplate;

    @GetMapping
    @ResponseBody
    public String getStatus() {
    	
    	String message = "MESAJ " + LocalTime.now();
    	String topic = "testTopic";
    	
        ListenableFuture<SendResult<Integer, String>> kafkaResultFuture = kafkaTemplate.send(topic, 1, message);
        LOG.info("Message sent async");

        kafkaResultFuture.addCallback(new ListenableFutureCallback<>() {
        	
            @Override
            public void onFailure(Throwable throwable) {
                LOG.error("Error sending ", throwable);
            }

            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                    RecordMetadata metadata = result.getRecordMetadata();
                    LOG.info("Partition {}, Offset {}.", metadata.partition(), metadata.offset());
            }
        });
        
        return "Sent to topic: " + topic;
    }
    
    @PreDestroy
    public void close() {
        if (kafkaTemplate != null) {
            LOG.info("Close producer!");
            kafkaTemplate.destroy();
        }
    }

    private static final Logger LOG = LoggerFactory.getLogger(ProducerRestService.class);
}