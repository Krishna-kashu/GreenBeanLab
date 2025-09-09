package com.mywork.onlinelearning.service;

public interface EmailSenderService {

    boolean sendOTP(String email, String otp);
}
