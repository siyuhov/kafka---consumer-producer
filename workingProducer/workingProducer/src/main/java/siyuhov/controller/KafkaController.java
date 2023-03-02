package siyuhov.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import siyuhov.other.KafkaProducer;
import siyuhov.other.MessageGen;
import siyuhov.service.SendDtoService;
import siyuhov.other.SpamMessage;
import siyuhov.Dto.UserDTO;
import siyuhov.service.SendFileService;

import java.io.IOException;

@RestController
@AllArgsConstructor
public class KafkaController {

    private final KafkaProducer kafkaProducer;
    private final MessageGen messageGen;
    private final SpamMessage spamMessage;
    private final SendDtoService sendDtoService;

    private final SendFileService sendFileService;

    @PostMapping("/spam")
    public void spamMessage(@RequestParam("message") String message) {
        spamMessage.start(message);
    }

    @PostMapping("/stop")
    public void stopSpam() {
        spamMessage.stop();
    }

    @PostMapping("/publish")
    public void writeMessageToTopic(@RequestParam("message") String message) {
        this.kafkaProducer.writeMessage(messageGen.createMessage(message));
    }

    @PostMapping("/createAndSendDTO")
    public ResponseEntity<String> sendDTO(@RequestBody UserDTO dto) throws JsonProcessingException {
        sendDtoService.sendDto(dto);
        return ResponseEntity.ok("DTO sent to Kafka topic");
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendFile(@RequestParam("file") MultipartFile file) throws IOException {
        sendFileService.sendFile(file);
        return ResponseEntity.ok(file.getOriginalFilename());
    }
}