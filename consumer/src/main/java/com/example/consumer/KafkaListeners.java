package com.example.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ResourceLoader;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class KafkaListeners {
    private final ObjectMapper objectMapper;
    int counter = 0;

    public KafkaListeners(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void receiveMessage(byte[] message) throws IOException {
        System.err.println(message);
        File file = new File("myfile_" + counter + ".jpg");
        file.createNewFile();
        System.err.println("Я  СЮДА ЗАХОЖУ");
        String filePath = "C:\\Users\\siyho\\IdeaProjects\\kafka---consumer-producer\\myfile_" + counter + ".jpg";
        Path path = Path.of(filePath);
        Files.write(path, message);
        counter++;
    }

    @KafkaListener(topics = "test", groupId = "admin")
    public void consume(String dto) throws IOException {
        try {
            UserDTO data = objectMapper.readValue(dto, UserDTO.class);
            System.err.println("Получено UserDTO :");
            System.err.println("Name - " + data.getName());
            System.err.println("Age - " + data.getAge());
            System.err.println("Role - " + data.getRole() + "\n");
        } catch (IOException ignored) {
            if (dto.contains("�")) {
                receiveMessage(dto.getBytes());
            } else {
                System.err.println("Получено сообщение :");
                System.err.println(dto + "\n");
            }
        }
    }
}
