package com.mywork.passport_seva.controller;

import com.mywork.passport_seva.dto.PassportDTO;
import com.mywork.passport_seva.service.PassportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class PassportController {

    @Autowired
    PassportServiceImpl service;


    public PassportController(){
        System.out.println("no-arg constructor of PassportController");
    }

    @GetMapping("index")
    public String redirect(){
        System.out.println("redirectToIndex");
        return "index";
    }

    @GetMapping("form")
    public String redirectToForm(){
        System.out.println("redirecting to Form page");
        return "formPage";
    }

    @PostMapping("submit")
    public String submit(PassportDTO dto, Model model){
        System.out.println("submit method in controller");
        System.out.println("saved dto: "+dto);

        if(service.save(dto)){
            System.out.println("valid dto");
            model.addAttribute("dto", dto);
        }else {
            System.out.println("not valid");
            return "index";
        }

        List<PassportDTO> passportDTOS = service.getAll();
        model.addAttribute("dtos", passportDTOS);
        return "getAll";
    }

    @GetMapping("allDetails")
    public String  getAll(Model model){

        System.out.println("invoking getAll in Controller");
        List<PassportDTO> passportDTOS = service.getAll();
        model.addAttribute("dtos", passportDTOS);
        return "getAll";
    }
}
