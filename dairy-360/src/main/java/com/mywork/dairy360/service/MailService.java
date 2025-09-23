package com.mywork.dairy360.service;

public interface MailService {

    void sendResetLink(String toEmail, String link);

    void sendMail(String toEmail, String subject, String body);
}
