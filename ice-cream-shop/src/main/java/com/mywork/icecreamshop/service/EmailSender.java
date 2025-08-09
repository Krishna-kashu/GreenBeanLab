package com.mywork.icecreamshop.service;

public interface EmailSender {
     void mailSend(String email, String subject, String message);
}
