package com.mywork.onlinelearning.controller;

import com.mywork.onlinelearning.dto.LearnerDTO;
import com.mywork.onlinelearning.entity.LearnerEntity;
import com.mywork.onlinelearning.service.EmailSenderServiceImpl;
import com.mywork.onlinelearning.service.LearnerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.PersistenceException;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/")
public class LearnerController {

    @Autowired
    private LearnerServiceImpl service;

    @Autowired
    private EmailSenderServiceImpl emailSenderService;

    public LearnerController(){
        System.out.println("\nno-arg constructor og LearnerController");
    }

    @GetMapping("index")
    public String redirectToIndex(){
        System.out.println("\nredirecting To Index page");
        return "index";
    }

    @GetMapping("registerPage")
    public String redirectToRegister(){
        System.out.println("\nredirect to register page");

        return "register";
    }

    @GetMapping("loginPage")
    public String login(){
        System.out.println("\n redirect to login page");
        return "login";
    }


    @PostMapping("register")
    public String register(LearnerDTO dto, Model model){
        System.out.println("\nregister method in controller, dto: "+dto);

        if (service.checkEmail(dto.getEmail()) != null){
            model.addAttribute("msg", "Email already exists");
            return "register";
        }
        if (service.checkPhone(dto.getPhone())!=null){
            model.addAttribute("msg", "Phone number already exists");
            return "register";
        }
        if (service.valid(dto)){
            System.out.println("valid dto, registration success");
            model.addAttribute("dto", dto);
            model.addAttribute("email", dto.getEmail());
            model.addAttribute("msg", "Registration successful. " +
                    "A temporary password has been sent to your email. Use it to login.");
            return "login";
        }
        else {
            System.out.println("invalid dto");
            model.addAttribute("msg", "Details not saved");
            return "register";
        }
    }

//    @PostMapping("/loginWithOtp")
//    public String loginWithOtp(@RequestParam("email") String email,
//                               @RequestParam("otp") String otp,
//                               Model model) {
//        if (service.loginWithOtpOrPassword(email, otp)) {
//            model.addAttribute("email", email);
//            return "resetPassword";
//        }
//        model.addAttribute("msg", "Invalid OTP");
//        return "loginWithOTP";
//    }


    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model) {

        LearnerEntity learner = service.getByEmail(email);

        if (learner != null && learner.getLockTime() != null &&
                LocalDateTime.now().isBefore(learner.getLockTime().plusMinutes(10))) {
            model.addAttribute("msg", "Account locked due to 3 failed attempts. Try again after 10 minutes.");
            return "login";
        }

        if (service.loginWithOtpOrPassword(email, password)) {
            if (learner.getLoginCount() < 0) {
                model.addAttribute("email", email);
                return "resetPassword";
            }

            model.addAttribute("dto", learner);
            model.addAttribute("email", email);
            return "success";
        }

        model.addAttribute("msg", "Invalid credentials");
        return "login";
    }

    @PostMapping("reset-password")
    public String resetPassword(@RequestParam String email,@RequestParam String password,
                                @RequestParam String confirmPassword, Model model){

        boolean reset = service.setPassword(email, password, confirmPassword);
        if (reset) {
            model.addAttribute("msg", "Password reset successful. Please login");
            return "login";
        } else {
            model.addAttribute("msg", "Password reset failed. Passwords do not match.");
            model.addAttribute("email", email);
            return "resetPassword";
        }
    }
}