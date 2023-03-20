package com.example.Challenge2.services;

import com.example.Challenge2.entities.EmailRequest;
import com.example.Challenge2.entities.EmailWithAttachmentRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SendMailServiceImpl implements SendMailService{

    @Autowired
    private JavaMailSender javaMailSender;

    public String sendTextEmail(EmailRequest emailRequest) {
        //receives the emailrequest as parameters and sets its properties in the SimpleMailMessage class then sends
        // them using JavaMail Sender
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailRequest.getTo());
        message.setSubject(emailRequest.getSubject());
        message.setText(emailRequest.getText());
        javaMailSender.send(message);
        return "Email sent successfully";
    }

    public String sendHtmlEmail(EmailRequest emailRequest) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(emailRequest.getTo());
            helper.setSubject(emailRequest.getSubject());
            helper.setText(emailRequest.getText(), true);
            javaMailSender.send(message);
            return "Email sent successfully";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Error sending email";
        }
    }

    public String sendHtmlEmailWithAttachment(EmailWithAttachmentRequest emailWithAttachmentRequest) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(emailWithAttachmentRequest.getTo());
            helper.setSubject(emailWithAttachmentRequest.getSubject());
            helper.setText(emailWithAttachmentRequest.getText(), true);
            FileSystemResource file = new FileSystemResource(emailWithAttachmentRequest.getAttachment());
            helper.addAttachment(file.getFilename(), file);
            javaMailSender.send(message);
            return "Email sent successfully";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Error sending email";
        }
    }
}
