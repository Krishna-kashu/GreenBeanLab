package com.mywork.onlinelearning.service;

public interface EmailSenderService {
    void sendOTP(String email, String otp);
}
