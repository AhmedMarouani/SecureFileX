package com.example.Challenge2.entities;

public class EmailWithAttachmentRequest extends EmailRequest{
    private String attachment;//file path

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
