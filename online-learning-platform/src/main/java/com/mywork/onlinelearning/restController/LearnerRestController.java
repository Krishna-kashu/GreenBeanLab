package com.mywork.onlinelearning.restController;

import com.mywork.onlinelearning.service.LearnerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LearnerRestController {
    @Autowired
    private LearnerServiceImpl service;
    public LearnerRestController(){
        System.out.println("LearnerRestController constructor");
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

    @GetMapping("phoneCheck")
    public String checkPhone(@RequestParam("phone") Long phone){
        System.out.println("checkPhone method in restController");
        Long existingPhone = service.checkPhone(phone);
        if(existingPhone == null){
            return "";
        }
        return "Phone number already registered";
    }


}
