package com.example.Challenge2.services;

import com.example.Challenge2.entities.EmailRequest;
import com.example.Challenge2.entities.EmailWithAttachmentRequest;

public interface SendMailService {
     String sendTextEmail(EmailRequest emailRequest);

     String sendHtmlEmail(EmailRequest emailRequest);

     String sendHtmlEmailWithAttachment(EmailWithAttachmentRequest emailWithAttachmentRequest);
}
