package com.foxnival.service.message;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public boolean sendEmail(String to, String name, String password) {
        boolean isMailSent = false;
        String emailContent = "<h3>Hello, " + name + "</h3> <br/>"
                 + "<p>Please find below your foxnival login credential </p> <br/>"
                 + "<p>Username: " + to + "<p>"
                 + "<p>Password: " + password + "</p> <br/><br/>"
                 + "<p>Thanks & regards,</p>"
                 + "Imtiyaj";

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            mimeMessage.setFrom(new InternetAddress("techimtiyaz7368@gmail.com"));
            mimeMessage.setRecipients(MimeMessage.RecipientType.TO, to);
            mimeMessage.setSubject("Foxnival login credential.");
            mimeMessage.setContent(emailContent, "text/html; charset=utf-8");
            javaMailSender.send(mimeMessage);
            isMailSent = true;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return isMailSent;
    }
}