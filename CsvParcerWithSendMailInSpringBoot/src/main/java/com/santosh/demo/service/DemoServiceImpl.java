package com.santosh.demo.service;

import com.santosh.demo.helper.EmailSendHelper;
import com.santosh.demo.model.Mail;
import com.santosh.demo.model.Product;
import com.santosh.demo.utility.CsvWritter;
import com.santosh.demo.utility.ExcelWriter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;

@Component
public class DemoServiceImpl implements DemoService{
    private final JavaMailSender javaMailSender;

    public DemoServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    @Override
    public void sendMail(Mail mail) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mail.getRecipient(), mail.getRecipient());
        msg.setSubject(mail.getSubject());
        msg.setText(mail.getMessage());
        javaMailSender.send(msg);
    }

    @Override
    public void sendMailWithAttachments(Mail mail) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo("to_@email");
        helper.setSubject("Testing from Spring Boot");
        helper.setText("Find the attached image", true);
        if(!mail.getAttachment().isEmpty()) {
            helper.addAttachment(mail.getAttachment(), new ClassPathResource(mail.getAttachment()));
        }
        //senMail();
        javaMailSender.send(msg);
    }

    @Override
    public void senMail() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1,"Santosh Kushwah",30));
        String headers ="ID, Name, Age";
        String tomail = "santoshkumar021990@gmail.com"; String subject = "Test";
        String messageBody = "Test";
        String file = CsvWritter.csvConverter(productList,headers);
        String file2 = ExcelWriter.excelWritter(productList);
        String attachemnt = file.concat(",").concat(file2);
        EmailSendHelper emailSendHelper = new EmailSendHelper();
        emailSendHelper.sendMailAttachment("Test",tomail,tomail,tomail,messageBody,attachemnt);
    }
}
