package com.mywork.bugreportsubmission.restController;

import com.mywork.bugreportsubmission.service.BugServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BugRestController {

    @Autowired
    BugServiceImpl service;

    public BugRestController() {
        System.out.println("no-arg constructor of BugRestController ");
    }

    @GetMapping("/emailCheck")
    public String checkMail(@RequestParam("email") String email){
        System.out.println("mail check method");
        System.out.println("restController email: "+email);

        boolean result = service.checkEmail(email);
        if(result){
            return "Email exists";
        }
        return "Email not exists";
    }

}
