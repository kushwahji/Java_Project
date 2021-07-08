package com.santosh.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.util.Properties;

public class EmailSendHelper {
    @Value("${mail.smtp.host}")
    private String host;
    @Value("${mail.smtp.port}")
    private String port;
    @Value("${mail.user}")
    private String username;
    @Value("${mail.password}")
    private String password;
    @Autowired
    EmailHelper emailHelper;
    public Session getEmailSession(){

        Properties properties = new Properties();
        Session session = null;
        try {
            String host = "smtp.gmail.com";
            String port = "587";
            String mailFrom = "kushwah.developer@gmail.com";
            String password = "Vihaan@24012018";
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", port);
            properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.user", mailFrom);
            properties.put("mail.password", password);
            Authenticator auth = new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mailFrom, password);
                }
            };
            session = Session.getInstance(properties, auth);
        }catch (Exception e){
            e.printStackTrace();
        }
        return session;
    }
    public boolean sendMailAttachment(String subject,String toEmail, String ccEmail, String bccEmail,String bodyMessage,String attachment){
        try {
            Session session = getEmailSession();
            Message message = new MimeMessage(session);
            setRecipients(message,toEmail,ccEmail,bccEmail);
            message.setSubject(subject);
            MimeBodyPart messageBody = new MimeBodyPart();
            messageBody.setContent(bodyMessage, "text/html");
            Multipart multipart = new MimeMultipart();
            setAttachments(attachment,messageBody,multipart,message);
            if(!attachment.isEmpty()){
                message.setContent(multipart);
            }
            EmailHelper emailHelper = new EmailHelper();
            emailHelper.sendEmail(message);
        }catch (Exception e){
            e.printStackTrace();;
        }
        return true;
    }

    private void setAttachments(String attachment, MimeBodyPart messageBody, Multipart multipart, Message message) {
        try {
            if (attachment != null) {
                try {
                    if(!attachment.isEmpty()) {
                        String[] attachmentArray = attachment.split(",");
                        for (String filePath : attachmentArray) {
                            DataSource source = new FileDataSource(filePath);
                            messageBody.setDataHandler(new DataHandler(source));
                            messageBody.setFileName(filePath);
                            multipart.addBodyPart(messageBody);
                          //  messageBody.attachFile(filePath);
                        }
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                multipart.addBodyPart(messageBody);
                message.setContent(multipart);
            }
        }catch (Exception e){
            e.printStackTrace();;
        }
    }

    private void setRecipients(Message message, String toEmail,String ccEmail , String bccEmail) {
        try {
            if(!toEmail.isEmpty()) {
                for (String to : toEmail.split(",")) {
                    message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
                }
            }
            if(!ccEmail.isEmpty()) {
                for (String cc : ccEmail.split(",")) {
                    message.setRecipient(Message.RecipientType.TO, new InternetAddress(cc));
                }
            }
            if(!bccEmail.isEmpty()) {
                for (String bcc : bccEmail.split(",")) {
                    message.setRecipient(Message.RecipientType.TO, new InternetAddress(bcc));
                }
            }
        }catch (Exception e){
            e.printStackTrace();;
        }
    }
}
