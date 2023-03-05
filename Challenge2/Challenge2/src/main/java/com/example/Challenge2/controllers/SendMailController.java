package com.example.Challenge2.controllers;

import com.example.Challenge2.entities.EmailRequest;
import com.example.Challenge2.entities.EmailWithAttachmentRequest;
import com.example.Challenge2.services.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMailController {

    @Autowired
    private SendMailService sendMailService;

    @PostMapping("/send/text")
    public ResponseEntity<String> sendTextEmail(@RequestBody EmailRequest emailRequest) {
        String message = sendMailService.sendTextEmail(emailRequest);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/send/html")
    public ResponseEntity<String> sendHtmlEmail(@RequestBody EmailRequest emailRequest) {
        String message = sendMailService.sendHtmlEmail(emailRequest);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/send/html/attachment")
    public ResponseEntity<String> sendHtmlEmailWithAttachment(@ModelAttribute EmailWithAttachmentRequest emailWithAttachmentRequest) {
        String message = sendMailService.sendHtmlEmailWithAttachment(emailWithAttachmentRequest);
        return ResponseEntity.ok(message);
    }
}
