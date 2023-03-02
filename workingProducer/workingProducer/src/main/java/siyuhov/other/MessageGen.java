package siyuhov.other;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MessageGen {
    public int a = 0;

    public String createMessage(String message) {
        a++;
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = dateFormat.format(date);
        message = a + " | " + message + "  | Date : " + currentTime;
        if (a%10_000==0){
            System.err.println(a);
        }
        return message;
    }
}
