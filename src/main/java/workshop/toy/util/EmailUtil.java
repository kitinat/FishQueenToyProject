package workshop.toy.util;


import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.Session;
import javax.mail.Transport;

public class EmailUtil {

    public EmailUtil(){

    }
    public static void sendEmail(String emailTo,String subject,String content)
    {
        // email ID of Recipient.
        String recipient = emailTo;

        // email ID of  Sender.
        String sender = "fishqueentoy@gmail.com";

        // using host as localhost
        String host = "smtp.gmail.com";
        String port = "587";
        boolean smtpServerTTLSEnabled = true;
        boolean authentication = true;
        String username = "fishqueentoy@gmail.com";
        String password = "Fq@12345";
        // Getting system properties
        Properties properties = System.getProperties();

        // Setting up mail server
        properties.put("mail.smtp.auth", String.valueOf(authentication));
        properties.put("mail.smtp.starttls.enable", smtpServerTTLSEnabled);
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");
        // creating session object to get properties
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        //session.setDebug(true);

        try {
            // MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From Field: adding senders email to from field.
            message.setFrom(new InternetAddress(sender));

            // Set To Field: adding recipient's email to from field.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            // Set Subject: subject of the email
            message.setSubject(subject);

            // set body of the email.
            //message.setText("This is a test mail");
            message.setText(content);

            // Send email.
            Transport.send(message);
            System.out.println("Mail successfully sent");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }


    }


}
