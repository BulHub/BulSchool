package ru.itis.services.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.itis.models.Sender;
import ru.itis.services.EmailService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@PropertySource("classpath:persistence.properties")
public class EmailServiceImpl implements EmailService {
    private static final Logger log = Logger.getLogger(EmailServiceImpl.class);
    private static int point = 0;
    private static Sender[] senders = {
            new Sender("instamendil777@gmail.com", "1234567890246813579A"),
            new Sender("instametdil1@gmail.com", "1234567890246813579B"),
            new Sender("instamendil3@gmail.com", "1234567890246813579A"),
            new Sender("instamendil4@gmail.com", "1234567890246813579A"),
            new Sender("instamendil5@gmail.com", "1234567890246813579A"),
            new Sender("instamendil6@gmail.com", "1234567890246813579A"),
            new Sender("instamendil7@gmail.com", "1234567890246813579A"),
            new Sender("instamendil8@gmail.com", "1234567890246813579A"),
            new Sender("instamendil9@gmail.com", "1234567890246813579A"),
            new Sender("instamendil10@gmail.com", "1234567890246813579A"),
            new Sender("instamendil11@gmail.com", "1234567890246813579A"),
            new Sender("instamendil12@gmail.com", "1234567890246813579A"),
            new Sender("instamendil13@gmail.com", "1234567890246813579A"),
    };

    @Value("${email.subject}")
    private String subjectEmail;

    @Value("${email.textRight}")
    private String textRight;

    @Value("${email.textWrong}")
    private String textWrong;

    @Override
    public synchronized void sendConfirmation(String toEmail, String token) {
        if (point == senders.length - 1){
            point = 0;
        }
        Properties props;
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senders[point].getUsername(), senders[point].getPassword());
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senders[point].getUsername()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subjectEmail);
            message.setText(textRight + token + "\n" + textWrong + token + "F");
            Transport.send(message);
            point++;
            log.info("The user with this mail sent a confirmation message: " + toEmail);
        } catch (MessagingException ex) {
            log.info("The user with this mail failed to send a confirmation message: " + toEmail);
        }
    }
}
