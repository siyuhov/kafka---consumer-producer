package siyuhov.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class KafkaProducer {

    private static final String TOPIC = "test";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateString;


    public void writeMessage (String message){
        kafkaTemplateString.send(TOPIC, message);
    }
}
