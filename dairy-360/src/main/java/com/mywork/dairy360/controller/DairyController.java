package com.mywork.dairy360.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class DairyController {


    public DairyController(){
        System.out.println("no-arg constructor of DairyController");
    }

    @GetMapping("index")
    public String getIndexPage()
    {
        System.out.println("redirected to Index page");
        return "index";
    }
    @GetMapping("login")
    public String redirectToLogin(){
        System.out.println("redirecting to login");

        return "login";
    }

    @GetMapping("products")
    public String product(){
        System.out.println("redirecting to product json");

        return "products";
    }


    @GetMapping("seller")
    public String redirectToSeller(){
        System.out.println("redirecting to seller");

        return "seller";
    }

    @GetMapping("contact")
    public String redirectToContact(){
        System.out.println("redirecting to Contact");

        return "contact";
    }
    @GetMapping("about")
    public String redirectToAbout(){
        System.out.println("redirecting to aboutUs");

        return "aboutUs";
    }

    @GetMapping("buyer")
    public String redirectToBuyer(){
        System.out.println("redirecting to Buyer");

        return "buyer";
    }

    @GetMapping("delivery")
    public String redirectToDelivery(){
        System.out.println("redirecting to delivery");

        return "delivery";
    }

}