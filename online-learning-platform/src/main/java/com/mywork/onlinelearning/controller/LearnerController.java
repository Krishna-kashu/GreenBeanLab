package com.mywork.onlinelearning.controller;

import com.mywork.onlinelearning.dto.LearnerDTO;
import com.mywork.onlinelearning.service.EmailSenderService;
import com.mywork.onlinelearning.service.EmailSenderServiceImpl;
import com.mywork.onlinelearning.service.LearnerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;

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

    @GetMapping("register")
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
            System.out.println("valid dto");
            model.addAttribute("msg", "Details Saved");
            model.addAttribute("dto", dto);
            model.addAttribute("email", dto.getEmail());
            return "verifyOTP";
        }
        else {
            System.out.println("invalid dto");
            model.addAttribute("msg", "Details not saved");
            return "register";
        }
    }

    @PostMapping("login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model){
        System.out.println("login method in controller");
        System.out.println("email: "+email+" - password: "+password);
        LearnerDTO dto = service.getUserDTO(email, password);
        if(dto==null){
            model.addAttribute("msg", "Invalid email or password");
            model.addAttribute("email", email);
            System.out.println("Details not found");

            return "login";
        }
        model.addAttribute("dto", dto);
        model.addAttribute("msg", "Login successful");
        System.out.println("Details Found");
        return "success";
    }

    @PostMapping("send-otp")
    public String sendOTP(@RequestParam String email, Model model){
        String otp = service.generateOTP(email);
        if (otp == null){
            model.addAttribute("msg", "Email not registered");
            return "register";
        }
        emailSenderService.sendOTP(email, otp);
        System.out.println("\n otp is:"+otp);
        model.addAttribute("msg", "OTP sent to "+email);
        model.addAttribute("email", email);
        return "verifyOTP";
    }

    @PostMapping("verify-otp")
    public String verifyOTP(@RequestParam String email, @RequestParam String otp, Model model){
        boolean verified = service.verifyOTP(email, otp);

        if (verified){
            model.addAttribute("email", email);
            model.addAttribute("msg", "OTP verified. You can now login.");
            return "resetPassword";
        }else {
            model.addAttribute("msg", "Invalid or expired OTP");
            model.addAttribute("email", email);
            return "verifyOTP";
        }
    }

    @PostMapping("reset-password")
    public String resetPassword(@RequestParam String email,@RequestParam String password,
                                @RequestParam String confirmPassword, Model model){

        boolean reset = service.resetPassword(email, password, confirmPassword);
        if (reset){
            model.addAttribute("msg", "Password reset successful. Please login");
            return "login";
        }else {
            model.addAttribute("msg", "Password reset failed. Make sure passwords match.");
            model.addAttribute("email", email);
            return "resetPassword";
        }
    }
}