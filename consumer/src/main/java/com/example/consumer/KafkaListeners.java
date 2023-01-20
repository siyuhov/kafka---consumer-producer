package com.example.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(
            topics = "topic",
    groupId = "admin")
    void listener(String data){
        System.out.println(data);
    }
}
