package com.mywork.bugreportsubmission.controller;

import com.mywork.bugreportsubmission.dto.BugDTO;
import com.mywork.bugreportsubmission.service.BugServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class BugController {

    @Autowired
    private BugServiceImpl service;

    public BugController(){
        System.out.println("\nno arg constructor of BugController ");
    }

    @GetMapping("index")
    public  String redirectToIndex(){
        System.out.println("redirect to index page");
        return "index";
    }

    @GetMapping("bugForm")
    public String redirectToForm(){
        System.out.println("\nredirecting to bugForm ");
        return "form";
    }

    @PostMapping("submitBug")
    public String submit(BugDTO dto, Model model){
        System.out.println("\n submit method is running..");
        System.out.println("dto: "+dto);

        if(service.validate(dto)){
            model.addAttribute("dto", dto);
        }else {
            System.out.println("invalid Bug dto");
            return "index";
        }

        List<BugDTO> bugList = service.getAll();
        model.addAttribute("bugs", bugList);
        return "listOfBug";
    }

    @GetMapping("getAll")
    public String getAll(Model model){
        System.out.println("get all method");
        List<BugDTO> bugList = service.getAll();
        model.addAttribute("bugs", bugList);
        return "listOfBug";
    }

    @GetMapping("gettingById")
    public  String getById(@RequestParam("bugId") Integer bugId, Model model){

        System.out.println("getById method is invoked..");
        BugDTO bugDTO = service.getById(bugId);

        model.addAttribute("dto", bugDTO);
        System.out.println("id is "+bugId);

        return "byId";
    }
}