package com.mywork.passport_seva.restController;

import com.mywork.passport_seva.service.PassportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PassportRestController {

    @Autowired
    private PassportServiceImpl service;

    @GetMapping("/mailCheck")
    public String mailExists(@RequestParam("email") String email){
        System.out.println("mailExists method in restController");
        System.out.println("restController email:"+email);

        boolean result = service.checkEmail(email);
        if(result){
            return "Email already exists";
        }
        return "Email not exists";
    }

    @GetMapping("/numberCheck")
    public  String checkPhoneNUmber(@RequestParam("phone") long phone){
        System.out.println("invoking checkPhoneNUmber method in restcontroller");
        System.out.println("phone number "+phone);

        boolean res = service.checkPhoneNumber(phone);
        if (res){
            return "Phone number already exists";
        }
        return "Valid phone number";
    }
}
