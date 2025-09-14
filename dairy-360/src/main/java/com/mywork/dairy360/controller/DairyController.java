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
    public String redirectToIndex(){
        System.out.println("redirecting to index");

        return "index";
    }

    @GetMapping("login")
    public String redirectToLogin(){
        System.out.println("redirecting to login");

        return "login";
    }

    @GetMapping("register")
    public String redirectToRegister(){
        System.out.println("redirecting to register");

        return "register";
    }

    @GetMapping("products")
    public String product(){
        System.out.println("redirecting to product");

        return "products";
    }

    @GetMapping("admin")
    public String redirectToAdminPage(){
        System.out.println("admin login");
        return "admin";
    }

    @GetMapping("adminLogin")
    public String redirectToAdminLogin(){
        System.out.println("redirecting to AdminLogin");

        return "adminLogin";
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

    public String register(){
        System.out.println("register in controller");

        return "login";
    }
}