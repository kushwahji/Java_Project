/*

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.IOException;
import java.util.*;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class H {

    public static void main(String[] args) throws IOException {


        String mailFrom = "kushwah.developer@gmail.com";
        // message info
        String mailTo = "santoshkumar021990@gmail.com";
        String subject = "New email with attachments";
        String message = "I have some attachments for you.";

        List<Product> productList = new ArrayList<>();

        FileWriter csvWriter1 = new FileWriter(fileName());
        productList.add(new Product(1,"Santosh Kushwah",30));
        productList.add(new Product(1,"Ravi Sharma",30));

        List<String[]> data = toStringArray(productList);
        for (String[] rowData : data) {
            csvWriter1.append(String.join(",", rowData));
            csvWriter1.append("\n");
        }

        csvWriter1.flush();
        csvWriter1.close();

        try {
            sendEmailWithAttachments( mailFrom, mailTo,
                    subject, message, fileName());
            System.out.println("Email sent.");
            File directory = new File(fileName());
            directory.deleteOnExit();
        } catch (Exception ex) {
            System.out.println("Could not send email.");
            ex.printStackTrace();
        }

    }

    private static List<String[]> toStringArray(List<Product> emps) {
        List<String[]> records = new ArrayList<String[]>();
        records.add(new String[] { "ID", "Name", "Age" });
        Iterator<Product> it = emps.iterator();
        while (it.hasNext()) {
            Product emp = it.next();
            records.add(new String[] {String.valueOf(emp.getId()), emp.getName(), String.valueOf(emp.getAge())});
        }
        return records;
    }
    public static void sendEmailWithAttachments(
            final String userName, String toAddress,
            String subject, String message, String attachFiles)
            throws AddressException, MessagingException {
        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "kushwah.developer@gmail.com";
        String password = "Vihaan@24012018";
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);

        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);

        // creates a new e-mail message
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());

        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");

        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        // adds attachments
        if (attachFiles != null) {

            MimeBodyPart attachPart = new MimeBodyPart();

            try {
                attachPart.attachFile(attachFiles);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            multipart.addBodyPart(attachPart);

        }

        // sets the multi-part as e-mail's content
        msg.setContent(multipart);

        // sends the e-mail
        Transport.send(msg);

    }
    public static String fileName() throws IOException {
        String PATH = "temp/";
        String fileName = PATH.concat("temp.csv");
        File file  = new File(fileName);
        File directory = new File(PATH);
        if(!directory.exists()){
            directory.mkdir();
            if(!file.exists()){
                file.getParentFile().mkdir();
                file.createNewFile();
            }
        }
        return fileName;
    }
}
*/
