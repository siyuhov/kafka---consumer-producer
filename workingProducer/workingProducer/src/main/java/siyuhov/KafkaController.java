package siyuhov;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@AllArgsConstructor
public class KafkaController {

    private final KafkaProducer kafkaProducer;

    @PostMapping("/publish")
    public void writeMessageToTopic(@RequestParam("message") String message) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = dateFormat.format(date);
        message = message + "  | Date : " + currentTime;
        this.kafkaProducer.writeMessage(message);
    }

    @PostMapping("/uploadFile")
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        System.out.println(file.getName());

        String filePath = "path/to/file";
        byte[] byteArray = null;
        try {
            byteArray = Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}
