package siyuhov.other;

import org.springframework.stereotype.Service;
import siyuhov.other.KafkaProducer;
import siyuhov.other.MessageGen;

@Service
public class SpamMessage {
    private final KafkaProducer kafkaProducer;
    private final MessageGen messageGen;
    volatile boolean spam = false;

    public SpamMessage(KafkaProducer kafkaProducer, MessageGen messageGen) {
        this.kafkaProducer = kafkaProducer;
        this.messageGen = messageGen;
    }

    public void start(String message) {
        var start = System.nanoTime();
        spam = true;
        for (int i = 0; i <= 1_000_000; i++) {
            kafkaProducer.writeMessage(messageGen.createMessage(message));
        }
        var end = System.nanoTime();
        var diff = (end - start)/1_000_000_000;
        System.err.println("МОЯ ОСТАНОВОЧКА - " + diff);
    }

    public void stop() {
        spam = false;
    }
}
