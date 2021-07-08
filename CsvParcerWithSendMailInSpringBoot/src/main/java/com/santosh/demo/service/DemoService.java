package com.santosh.demo.service;

import com.santosh.demo.model.Mail;

import javax.mail.MessagingException;

public interface DemoService {
    void sendMail(Mail mail);
    void sendMailWithAttachments(Mail mail) throws MessagingException;
    void senMail();
}
