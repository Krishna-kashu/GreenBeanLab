package com.mywork.onlinelearning.controller;

import com.mywork.onlinelearning.dto.LearnerDTO;
import com.mywork.onlinelearning.service.LearnerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class LearnerController {

    @Autowired
    private LearnerServiceImpl service;

    public LearnerController(){
        System.out.println("\nno-arg constructor og LearnerController");
    }

    @GetMapping("index")
    public String redirectToIndex(){
        System.out.println("\nredirecting To Index page");
        return "index";
    }

    @GetMapping("registerPage")
    public String redirectToRegister(){
        System.out.println("\nredirect to register page");

        return "register";
    }

    @GetMapping("loginPage")
    public String login(){
        System.out.println("\n redirect to login page");
        return "login";
    }

    @GetMapping("register")
    public String register(LearnerDTO dto, Model model){
        System.out.println("\nregister method in controller, dto: "+dto);


        if (service.checkEmail(dto.getEmail()) != null){
            model.addAttribute("msg", "Email already exists");
            return "register";
        }
        if (service.checkPhone(dto.getPhone())!=null){
            model.addAttribute("msg", "Phone number already exists");
            return "register";
        }
        if (service.valid(dto)){
            System.out.println("valid dto");
            model.addAttribute("msg", "Details Saved");
            model.addAttribute("dto", dto);
            return "success";
        }
        else {
            System.out.println("invalid dto");
            model.addAttribute("msg", "Details not saved");
            return "register";
        }
    }

    @PostMapping("login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model){
        System.out.println("login method in controller");
        System.out.println("email: "+email+" - password: "+password);
        LearnerDTO dto = service.getUserDTO(email, password);
        if(dto==null){
            model.addAttribute("msg", "Invalid email or password");
            model.addAttribute("email", email);
            System.out.println("Details not found");

            return "login";
        }
//        if (dto.getEmail()==null){
//            model.addAttribute("msg", "Invalid email");
//            System.out.println("Email not found");
//            return "login";
//        }
//        if (dto.getPassword()==null){
//            model.addAttribute("msg", "Invalid password");
//            System.out.println("Password not found");
//            return "login";
//        }
        model.addAttribute("dto", dto);
        model.addAttribute("msg", "Login successful");
        System.out.println("Details Found");
        return "success";
    }
}
