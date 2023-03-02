package siyuhov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SendFileService {
    @Autowired
    private KafkaTemplate<byte[], byte[]> kafkaTemplateByteArray;

    private final String TOPIC = "test";

    public void sendFile(MultipartFile file) throws IOException {
        byte[] fileContent = file.getBytes();
        kafkaTemplateByteArray.send(TOPIC, fileContent);
    }
}
