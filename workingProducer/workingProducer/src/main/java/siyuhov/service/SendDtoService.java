package siyuhov.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import siyuhov.Dto.UserDTO;

@Service
public class SendDtoService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateString;
    private final String TOPIC = "test";

    public void sendDto(UserDTO dto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(dto);
        kafkaTemplateString.send(TOPIC, json);
    }
}
