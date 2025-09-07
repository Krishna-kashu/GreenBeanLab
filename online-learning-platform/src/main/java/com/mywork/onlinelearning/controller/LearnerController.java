package com.mywork.onlinelearning.controller;

import com.mywork.onlinelearning.dto.LearnerDTO;
import com.mywork.onlinelearning.entity.LearnerEntity;
import com.mywork.onlinelearning.service.EmailSenderServiceImpl;
import com.mywork.onlinelearning.service.LearnerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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
            LearnerDTO dto = service.getByEmailDTO(email);
            model.addAttribute("dto", dto);
            model.addAttribute("email", email);
            return "success";
        }

        model.addAttribute("msg", "Invalid credentials");
        return "login";
    }

    @GetMapping("dashboard")
    public String dashboard(@RequestParam("email") String email, Model model) {
        LearnerDTO dto = service.getByEmailDTO(email);
        if (dto == null) return "login";
        model.addAttribute("dto", dto);
        return "dashboard";
    }

    @PostMapping("reset-password")
    public String resetPassword(@RequestParam String email,
                                @RequestParam String password,
                                @RequestParam String confirmPassword,
                                Model model){

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

    @PostMapping("update")
    public String updateDashboard(LearnerDTO dto, Model model) {
        System.out.println("Updating dashboard for DTO: " + dto);

        if(dto.getLearnerId() == null) {
            model.addAttribute("msg", "Invalid request: User ID missing");
            return "dashboard";
        }

        LearnerDTO existing = service.getByID(dto.getLearnerId());
        if(existing == null) {
            model.addAttribute("msg", "User not found");
            return "dashboard";
        }

        existing.setName(dto.getName());
        existing.setGender(dto.getGender());
        existing.setState(dto.getState());
        existing.setDob(dto.getDob());
        existing.setPhone(dto.getPhone());
        existing.setAddress(dto.getAddress());

        String result = service.updateEntity(existing);
        model.addAttribute("dto", existing);
        model.addAttribute("msg", result.equals("Updated") ? "Profile updated successfully" : "Update failed");

        return "dashboard";
    }
    @PostMapping("uploadProfileImage")
    public String uploadProfileImage(@RequestParam("email") String email,
                                     @RequestParam("profileImage") MultipartFile file,
                                     HttpServletRequest request,
                                     Model model)  {

        LearnerDTO dto = service.getByEmailDTO(email);
        if (dto == null) {
            model.addAttribute("msg", "Invalid user. Please login again.");
            return "login";
        }

        if (!file.isEmpty()) {
            try {
                String uploadDir = request.getServletContext().getRealPath("/uploads/");
                File dir = new File(uploadDir);
                if (!dir.exists()) dir.mkdirs();

                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                File dest = new File(dir, fileName);

                file.transferTo(dest);

                dto.setProfileImage("uploads/" + fileName);
                service.updateEntity(dto);

                model.addAttribute("dto", dto);
                model.addAttribute("msg", "Profile picture uploaded successfully.");

                return "dashboard";

            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("msg", "Error uploading profile picture.");
            }
        } else {
            model.addAttribute("msg", "No file selected.");
        }

        model.addAttribute("dto", dto);
        return "dashboard";
    }

    @GetMapping("resend-otp")
    public String resendOtp(@RequestParam String email, Model model) {
        LearnerEntity learner = service.getByEmail(email);

        if (learner == null || !learner.getIsActive()) {
            model.addAttribute("msg", "Invalid email or user not active.");
            return "login";
        }

        LocalDateTime lastSent = learner.getOtpGeneratedTime();
        if (lastSent != null && LocalDateTime.now().isBefore(lastSent.plusMinutes(2))) {
            long secondsLeft = java.time.Duration.between(LocalDateTime.now(), lastSent.plusMinutes(2)).getSeconds();
            model.addAttribute("msg", "Please wait " + secondsLeft + " seconds before resending OTP.");
            model.addAttribute("email", email);
            return "login";
        }

        String newOtp = String.valueOf((int)(Math.random() * 900000) + 100000);
        learner.setPassword(new BCryptPasswordEncoder().encode(newOtp));
        learner.setOtpGeneratedTime(LocalDateTime.now());

        service.saveUpdatedLearner(learner);

        emailSenderService.sendOTP(email, newOtp);
        model.addAttribute("msg", "OTP resent successfully.");
        model.addAttribute("email", email);
        return "login";
    }


    @GetMapping("success")
    public String success(@RequestParam("email") String email, Model model) {
        LearnerDTO dto = service.getByEmailDTO(email);
        if (dto == null) {
            model.addAttribute("msg", "Please login again.");
            return "login";
        }
        model.addAttribute("dto", dto);
        return "success";
    }

}