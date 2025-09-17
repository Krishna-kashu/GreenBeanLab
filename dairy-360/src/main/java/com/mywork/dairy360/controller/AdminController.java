package com.mywork.dairy360.controller;

import com.mywork.dairy360.dto.AdminDTO;
import com.mywork.dairy360.service.AdminService;
import com.mywork.dairy360.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private MailService mailService;

    public AdminController(){
        System.out.println("no-arg constructor of AdminController");
    }

    @GetMapping("/forgot-password")
    public String forgotPasswordPage() {
        return "forgotPassword";
    }

    @GetMapping("admin")
    public String redirectToAdminPage(){
        System.out.println("admin Dashboard");
        return "admin";
    }


    @GetMapping("admin-login")
    public String redirectToAdminLogin(){
        System.out.println("redirecting to AdminLogin");

        return "adminLogin";
    }

    @GetMapping("admin-register")
        public String redirectToAdminRegisterPage(){
            System.out.println("redirecting to AdminRegisterPage");

            return "adminRegister";
        }


    @PostMapping("/adminLogin")
    public String checkPasswordForAdminLogin(@RequestParam String email,
                                             @RequestParam String password,
                                             Model model) {
        System.out.println("checkPasswordForAdminLogin method in adminController");
        System.out.println("Email: " + email + " password: " + password);
        AdminDTO adminDTO = adminService.checkAdminLoginPassword(email, password);
        if (adminDTO != null) {
            model.addAttribute("dto", adminDTO);
            return "admin";
        } else {
            model.addAttribute("errorMessage", "Password / email incorrect");
            return "adminLogin";
        }
    }

    @GetMapping("/redirectToUpdateAdminProfile")
    public String redirectToUpdate(@RequestParam("email") String email, Model model) {
        System.out.println("redirectTo update page in adminController");
        AdminDTO adminDTO = adminService.getAdminDetailsByEmail(email);
        model.addAttribute("dto", adminDTO);
        return "admin";
    }

    @GetMapping("/redirectToAdminDashboard")
    public String getDashboard(@RequestParam("email") String email, Model model) {
        AdminDTO adminDTO = adminService.getAdminDetailsByEmail(email);
        model.addAttribute("dto", adminDTO);
        return "admin";
    }

    @PostMapping("/updateAdminProfile")
    public String updateAdminProfile(
            @RequestParam("adminName") String adminName,
            @RequestParam("email") String email,
            @RequestParam("phoneNumber") String phoneNumber,
            Model model) {

        System.out.println("updateAdminProfile method in adminController");

        if (adminService.updateAdminProfileByEmail(email, adminName, phoneNumber)) {
            System.out.println(" updated");
            return getDashboard(email, model);
        }
        model.addAttribute("errorMessage", "Invalid details");

        return redirectToUpdate(email,model);
    }

    @GetMapping("/adminLogout")
    public String adminLogoutIndexPage()
    {
        System.out.println("adminLogoutIndexPage method in adminController");
        return "index";
    }

    @PostMapping("/adminRegister")
    public String saveAdminFromForm(
            @RequestParam("adminName") String adminName,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword,
            Model model) {

        AdminDTO dto = new AdminDTO();
        dto.setAdminName(adminName);
        dto.setPhoneNumber(phoneNumber);
        dto.setEmail(email);
        dto.setPassword(password);
        dto.setConfirmPassword(confirmPassword);

        if (adminService.save(dto)) {
            model.addAttribute("successMessage", "Admin registered successfully! Please login.");
            return "adminLogin";
        } else {
            model.addAttribute("errorMessage", "Registration failed (Passwords didn’t match or duplicate email/phone).");
            return "adminRegister";
        }
    }

    @PostMapping("/sendResetLink")
    public String sendResetLink(@RequestParam String email, Model model) {
        AdminDTO admin = adminService.getAdminDetailsByEmail(email);
        if (admin == null) {
            model.addAttribute("errorMessage", "Email not found");
            return "forgotPassword";
        }

        String token = java.util.UUID.randomUUID().toString();
        String link = "http://localhost:8080/dairy-360/reset-password?token=" + token;

        adminService.saveResetToken(email, token);

        mailService.sendResetLink(email, link);

        model.addAttribute("successMessage", "Reset link sent to your email!");
        return "forgotPassword";
    }

    @GetMapping("/reset-password")
    public String resetPasswordPage(@RequestParam String token, Model model) {
        if (!adminService.isValidResetToken(token)) {
            model.addAttribute("errorMessage", "Invalid or expired token");
            return "forgotPassword";
        }
        model.addAttribute("token", token);
        return "resetPassword";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String token,
                                @RequestParam String password,
                                @RequestParam String confirmPassword,
                                Model model) {
        if (!password.equals(confirmPassword)) {
            model.addAttribute("errorMessage", "Passwords do not match");
            model.addAttribute("token", token);
            return "resetPassword";
        }
        if (adminService.updatePassword(token, password)) {
            model.addAttribute("successMessage", "Password reset successful! Please login.");
            return "adminLogin";
        }
        model.addAttribute("errorMessage", "Invalid or expired token");
        return "resetPassword";
    }
}