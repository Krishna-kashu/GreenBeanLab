package com.mywork.shop_app.restController;

import com.mywork.shop_app.service.ShopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MailRestController {

    @Autowired
    private ShopServiceImpl service;

    @GetMapping("/checkEmail")
    public  String loginMailExists(@RequestParam("mail") String email){
        System.out.println("calling rest ");
        System.out.println("emaill:"+email);
          boolean result = service.mailExits(email);

        if(result){
            return "Email already exists";
        }
        return "Email not exists";
    }
}
