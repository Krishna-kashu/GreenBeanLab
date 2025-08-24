package com.mywork.newsletter.restController;

import com.mywork.newsletter.service.NewsLetterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class NewsRestController {

    @Autowired
    NewsLetterServiceImpl service;

    public NewsRestController() {
        System.out.println("no-arg constructor of NewsRestController");
    }

    @GetMapping("/check")
    public String mailCheck(@RequestParam("email") String email){
        System.out.println("mailCheck method in restController, email: "+email);
        boolean res = service.checkMail(email);
        if (res){
            return "email exists";
        }
        return "email not exists";
    }
}
