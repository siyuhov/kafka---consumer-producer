package siyuhov;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class KafkaProducer {

    private static final String TOPIC = "topic";

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;


    public void writeMessage (String message){
        this.kafkaTemplate.send(TOPIC, message);
    }
}
