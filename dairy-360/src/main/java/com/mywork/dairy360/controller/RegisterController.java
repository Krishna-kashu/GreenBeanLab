package com.mywork.dairy360.controller;

import com.mywork.dairy360.dto.RegisterDTO;
import com.mywork.dairy360.entity.RegisterEntity;
import com.mywork.dairy360.service.MailServiceImpl;
import com.mywork.dairy360.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private MailServiceImpl mailService;

    public RegisterController(){
        System.out.println("Register controller constructor");
    }

    @GetMapping("reg")
    public String redirectToRegisterPage(){
        System.out.println("Register Page");
        return "register";
    }

    @PostMapping("register")
    public String saveRegister(@ModelAttribute RegisterDTO dto, Model model) {
        System.out.println("Saving user: " + dto);

        boolean saved = registerService.saveUser(dto);

        if (saved) {
            model.addAttribute("successMessage", "Registration successful! Please login.");
            return "login";
        } else {
            model.addAttribute("errorMessage", "Something went wrong. Try again!");
            return "register";
        }
    }

    @PostMapping("login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            @RequestParam String role,
                            Model model) {
        System.out.println("loginUser method in RegisterController");
        RegisterEntity user = registerService.getUserByEmail(email);

        if (user != null && user.getPassword().equals(password) && user.getRole().equals(role)) {
            if ("Buyer".equals(role)) return "buyer";
            if ("Seller".equals(role)) return "seller";
            if ("Admin".equals(role)) return "admin";
            if ("Delivery".equals(role)) return "delivery";
        }

        model.addAttribute("errorMessage", "Invalid email / password / role");
        return "login";
    }

    @GetMapping("/user/forgot-password")
    public String forgotPasswordPage() {
        System.out.println("forgotPasswordPage method in controller");
        return "forgotPassword";
    }

    @PostMapping("/user/forgot-password")
    public String sendResetLink(@RequestParam String email, Model model) {
        System.out.println("sendResetLink RegisterController");
        RegisterEntity entity = registerService.getByEmail(email);
        if (entity == null) {
            model.addAttribute("msg", "Email not found");
            return "forgotPassword";
        }
        String resetToken = UUID.randomUUID().toString();
        registerService.saveResetToken(entity, resetToken);

        entity.setResetTokenExpiry(LocalDateTime.now().plusMinutes(10));
        registerService.update(entity);

        String resetLink = "http://localhost:8080/reset-password?token=" + resetToken;
        mailService.sendResetLink(email, resetLink);

        model.addAttribute("msg", "Reset link sent to email");
        return "forgotPassword";
    }
    @GetMapping("/user/reset-password")
    public String resetPasswordPage(@RequestParam String token, Model model) {
        System.out.println("resetPasswordPage method in RegisterController");
        RegisterEntity entity = registerService.getByResetToken(token);
        if (entity == null || entity.getResetTokenExpiry().isBefore(LocalDateTime.now())) {
            model.addAttribute("msg", "Invalid or expired token");
            return "resetPassword";
        }
        model.addAttribute("email", entity.getEmail());
        return "resetPassword";
    }

    @PostMapping("/user/reset-password")
    public String processReset(@RequestParam("token") String token,
                               @RequestParam("password") String password,
                               Model model) {
        System.out.println("processReset method in \t RegisterController");
        RegisterEntity user = registerService.getByResetToken(token);

        if (user == null) {
            model.addAttribute("error", "Invalid or expired token");
            return "resetPassword";
        }
        registerService.updatePassword(user, password);

        model.addAttribute("message", "Password reset successful. Please login.");
        return "login";
    }
}
