package com.mywork.dairy360.restController;

import com.mywork.dairy360.dto.RegisterDTO;
import com.mywork.dairy360.service.RegisterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/reg")
public class RegisterRestController {

    @Autowired
    private RegisterServiceImpl service;

    public RegisterRestController(){
        System.out.println("register controller ");
    }

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO registerDTO) {
        service.saveUser(registerDTO);
        return ResponseEntity.ok(
                Collections.singletonMap("message", "User registered successfully")
        );
    }

    @GetMapping("mailCheck")
    public String checkMail(@RequestParam("email") String email){
        System.out.println("checkMail method in restController");
        String existingMail = service.checkEmail(email);
        System.out.println("Email : "+existingMail);
        if (existingMail==null){
            return "";
        }
        else {
            return "Email Registered";
        }
    }
}