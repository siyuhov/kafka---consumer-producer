
package com.example.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class KafkaListeners {
    private final ObjectMapper objectMapper;
    int counter = 0;

    public KafkaListeners(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @KafkaListener(topics = "file", groupId = "group1")
    public void readFile(byte[] fileMessage) throws IOException {
        counter++;
        File outputFile = new File("image" + counter + ".jpg");
        FileOutputStream outputStream = new FileOutputStream(outputFile);
        System.err.println("Получен файл : " + outputFile.getName());
        outputStream.write(fileMessage);
        outputStream.close();
    }

    @KafkaListener(topics = "test", groupId = "admin")
    public void consume(String dto) {
        try {
            UserDTO data = objectMapper.readValue(dto, UserDTO.class);
            System.err.println("Получено UserDTO :");
            System.err.println("Name - " + data.getName());
            System.err.println("Age - " + data.getAge());
            System.err.println("Role - " + data.getRole() + "\n");
        } catch (IOException ignored) {
            System.err.println("Получено сообщение :");
            System.err.println(dto + "\n");
        }
    }
}
