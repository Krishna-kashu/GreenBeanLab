package com.mywork.onlinelearning.service;

import com.mywork.onlinelearning.config.EmailConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    @Autowired
    private EmailConfiguration emailConfiguration;

    public EmailSenderServiceImpl() {
        System.out.println("EmailSenderServiceImpl constructor");
    }

    @Override
    public boolean sendOTP(String email, String otp) {
        System.out.println("Invoking sendOTP method in EmailSenderServiceImpl");
        System.out.println("mail: " + email + " otp: " + otp);

        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject("Online Learning Platform");
            simpleMailMessage.setText("OTP for login Online Learning Platform: " + otp);

            emailConfiguration.javaMailSender().send(simpleMailMessage);

            System.out.println("OTP sent successfully to: " + email + " : " + otp);
            return true;

        } catch (Exception e) {
            System.out.println("Failed to send OTP to " + email + " due to: " + e.getMessage());
            return false;
        }
    }
}