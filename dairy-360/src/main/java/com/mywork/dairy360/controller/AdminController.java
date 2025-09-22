package com.mywork.dairy360.controller;

import com.mywork.dairy360.dto.AdminDTO;
import com.mywork.dairy360.entity.AdminEntity;
import com.mywork.dairy360.service.AdminService;
import com.mywork.dairy360.service.AuditServiceImpl;
import com.mywork.dairy360.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;

@Controller
@RequestMapping("/")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private MailService mailService;

    @Autowired
    private AuditServiceImpl auditService;

    public AdminController(){
        System.out.println("no-arg constructor of AdminController AdminController");
    }

    @GetMapping("/admin/forgot-password")
    public String forgotPasswordPage() {
        System.out.println("redirecting to forgotPasswordPage, method from AdminController");
        return "forgotPassword";
    }

    @GetMapping("admin")
    public String redirectToAdminPage(@RequestParam(value="email", required=false) String email, Model model){
        System.out.println("admin Dashboard method from AdminController");
        if(email != null) {
            AdminDTO dto = adminService.getAdminDetailsByEmail(email);
            model.addAttribute("dto", dto);
        }
        return "admin";
    }


    @GetMapping("/admin-logout")
    public String adminLogoutIndexPage(@RequestParam(required = false) String email) {
        System.out.println("adminLogoutIndexPage method in AdminController");

        if (email != null && !email.isEmpty()) {
            AdminEntity admin = adminService.getAdminEntityByEmail(email);
            if (admin != null) {
                auditService.logLogout(admin);
            }
        }
        return "index";
    }

    @GetMapping("admin-login")
    public String redirectToAdminLogin(){
        System.out.println("redirecting to AdminLogin\t method from AdminController");

        return "adminLogin";
    }

    @PostMapping("admin-login")
    public String checkPasswordForAdminLogin(@RequestParam String email,
                                             @RequestParam String password,
                                             HttpSession session,
                                             Model model) {
        System.out.println("checkPasswordForAdminLogin method in adminController");
        System.out.println("Email: " + email + " password: " + password);
        AdminDTO adminDTO = adminService.checkAdminLoginPassword(email, password);
        if (adminDTO != null) {
            model.addAttribute("dto", adminDTO);

            session.setAttribute("adminEmail", email);
            session.setAttribute("adminName", adminDTO.getAdminName());

            AdminEntity adminEntity = adminService.getAdminEntityByEmail(email);
            auditService.logLogin(adminEntity);

            return "admin";
        } else {
            AdminDTO admin = adminService.getAdminDetailsByEmail(email);
            if (admin != null && !admin.getAccountNonLocked()) {
                model.addAttribute("errorMessage",
                        "Account locked due to 3 failed attempts. " +
                                "Please reset your password or wait 2 minutes.");
                model.addAttribute("showReset", true);


            } else {
                model.addAttribute("errorMessage", "Password / email incorrect");
            }
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
        System.out.println("getDashboard \t method from AdminController");
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
            model.addAttribute("successMessage", "Profile updated successfully!");
            return getDashboard(email, model);
        }
        model.addAttribute("errorMessage", "Update failed");

        return redirectToUpdate(email,model);
    }

    @GetMapping("admin-register")
    public String redirectToAdminRegisterPage(){
        System.out.println("redirecting to AdminRegisterPage");

        return "adminRegister";
    }

    @PostMapping("/admin-register")
    public String saveAdminFromForm(
            @RequestParam("adminName") String adminName,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword,
            Model model) {

        System.out.println("saveAdminFromForm method from AdminController");
        AdminDTO dto = new AdminDTO();
        dto.setAdminName(adminName);
        dto.setPhoneNumber(phoneNumber);
        dto.setEmail(email);
        dto.setPassword(password);
        dto.setConfirmPassword(confirmPassword);

        if (adminService.save(dto)) {
            System.out.println("admin registered successfully");
            model.addAttribute("successMessage", "Admin registered successfully! Please login.");
            return "adminLogin";
        } else {
            model.addAttribute("errorMessage", "Registration failed (Passwords didn’t match or duplicate email/phone).");
            return "adminRegister";
        }
    }

    @PostMapping("/admin/forgot-password")
    public String sendResetLink(@RequestParam String email, Model model) {
        System.out.println("sendResetLink method in Admin Controller");
        AdminDTO admin = adminService.getAdminDetailsByEmail(email);
        if (admin == null) {
            System.out.println("mail not found");
            model.addAttribute("errorMessage", "Email not found");
            return "forgotPassword";
        }

        String token = java.util.UUID.randomUUID().toString();
//        String link = "http://localhost:8080/admin/reset-password?token=" + token;

        String link = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/admin/reset-password")
                .queryParam("token", token)
                .toUriString();

        adminService.saveResetToken(email, token);

        mailService.sendResetLink(email, link);

        model.addAttribute("successMessage", "Reset link sent to your email!");
        return "forgotPassword";
    }

    @GetMapping("/admin/reset-password")
    public String resetPasswordPage(@RequestParam String token, Model model) {
        if (!adminService.isValidResetToken(token)) {
            System.out.println("resetPasswordPage from AdminController");
            model.addAttribute("errorMessage", "Invalid or expired token");
            return "forgotPassword";
        }
        model.addAttribute("token", token);
        return "resetPassword";
    }

    @PostMapping("/admin/reset-password")
    public String resetPassword(@RequestParam String token,
                                @RequestParam String password,
                                @RequestParam String confirmPassword,
                                Model model) {
        System.out.println("\nresetPassword method \t from AdminController");
        if (!password.equals(confirmPassword)) {
            System.out.println("\n Passwords do not match");
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

    @PostMapping("/admin/uploadProfileImage")
    public String uploadProfileImage(
            @RequestParam("email") String email,
            @RequestParam("profileImage") MultipartFile file,
            HttpServletRequest request,
            Model model) {

        System.out.println("\nuploadProfileImage method from AdminController");
        if (file.isEmpty()) {
            System.out.println("image not selected");
            model.addAttribute("errorMessage", "Please select an image.");
            return getDashboard(email, model);
        }

        try {
            String uploadDir = "C:/dairy/uploads";
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File saveFile = new File(uploadDir, fileName);
            saveFile.getParentFile().mkdirs();
            file.transferTo(saveFile);

            String dbPath = "uploads/" + fileName;
            adminService.updateProfileImage(email, dbPath);

            AdminDTO updated = adminService.getAdminDetailsByEmail(email);
            model.addAttribute("dto", updated);
            System.out.println("image updated successfully");
            model.addAttribute("successMessage", "Profile picture updated successfully!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error uploading file: " + e.getMessage());
        }

        return "admin";
    }
}