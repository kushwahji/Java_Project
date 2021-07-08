package com.santosh.helper;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

@Component
public class EmailHelper {
    @Async("email-async")
    public void sendEmail(Message message) {
        try {
            Transport.send(message);
            System.out.println("Email Send Successfully");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
