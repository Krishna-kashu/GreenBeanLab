package com.mywork.icecreamshop.service;

import com.mywork.icecreamshop.config.EmailConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSenderImpl implements  EmailSender{

//    @Autowired
//    EmailConfiguration emailConfiguration;

    @Autowired
    private JavaMailSender javaMailSender;


//    @Override
//    public void mailSend(String email, String otp) {
//
//        System.out.println("Invoking mailSend ");
//        System.out.println("mail:"+email+"otp: "+otp);
//        SimpleMailMessage simpleMailMessage= new SimpleMailMessage();
//        simpleMailMessage.setTo(email);
//        simpleMailMessage.setSubject("Otp to login");
//        simpleMailMessage.setText("Otp for login "+otp);
//        emailConfiguration.emailSender().send(simpleMailMessage);
//        System.out.println("Otp sent for :"+email+" : "+otp);
//    }

    @Override
    public void mailSend(String email, String subject, String message) {

        System.out.println("Invoking mailSend ");
        System.out.println("Sending email to: " + email);

        SimpleMailMessage simpleMailMessage= new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
//added to send gif file
        javaMailSender.send(simpleMailMessage);
//        emailConfiguration.emailSender().send(simpleMailMessage);
        System.out.println("Email sent successfully to " + email);
    }
}
