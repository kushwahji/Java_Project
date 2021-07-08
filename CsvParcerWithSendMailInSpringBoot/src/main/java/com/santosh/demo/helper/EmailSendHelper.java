package com.santosh.demo.helper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
@Component
public class EmailSendHelper {
    @Autowired
    EmailHelper emailHelper;
    public Session getEmailSession(){

        Properties properties = new Properties();
        Session session = null;
        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "vihaan24012021@gmail.com";
        String password = "Vihaan@24012021";
        try {
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", port);
            properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.user", mailFrom);
            properties.put("mail.password", password);
            Authenticator auth = new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("username", "Vihaan@24012018");
                }
            };
            session = Session.getInstance(properties, auth);
        }catch (Exception e){
            e.printStackTrace();
        }
        return session;
    }
    public boolean sendMail(String subject,String toEmail, String ccEmail, String bccEmail,String bodyMessage){
        try {
            Session session = getEmailSession();
            Message message = new MimeMessage(session);
            setRecipients(message,toEmail,ccEmail,bccEmail);
            message.setSubject(subject);
            MimeBodyPart messageBody = new MimeBodyPart();
            messageBody.setContent(bodyMessage, "text/html");
            EmailHelper emailHelper = new EmailHelper();
            emailHelper.sendEmail(message);
        }catch (Exception e){
            e.printStackTrace();;
        }
        return true;
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
                            byte[] encodeFile =encodeFileToBase64(filePath.trim());
                            if(null!=encodeFile){
                                messageBody = new MimeBodyPart();
                                ByteArrayDataSource dataSource = new ByteArrayDataSource(encodeFile,"text/alternative");
                                messageBody.setDataHandler(new DataHandler(dataSource));
                                messageBody.setFileName(getFileInstance(filePath.trim()).getName());
                                multipart.addBodyPart(messageBody);
                                message.setContent(multipart);
                            }
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

    private byte[] encodeFileToBase64(String filepath) {
        byte [] encoded = null;
        try{
            encoded = loadFile(getFileInstance(filepath));
        }catch (Exception io){
            io.printStackTrace();
        }
        return encoded;
    }

    private File getFileInstance(String fileName) {
        File file = null;
        try {
            if(!StringUtils.isEmpty(fileName)){
                file = new File(fileName);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return file;
    }

    private byte[] loadFile(File file) {
        byte[] bytes = null;
        try (InputStream is = new FileInputStream(file)) {
           long length = file.length();
           bytes = new byte[(int)length];
           int offset =0;
           int numRead =0;
           while (offset<bytes.length && (numRead = is.read(bytes,offset,bytes.length-offset))>=0){
               offset=numRead;
           }
        }catch (Exception e){
            e.printStackTrace();
        }
        return bytes;
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
