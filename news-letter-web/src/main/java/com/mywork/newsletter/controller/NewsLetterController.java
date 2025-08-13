package com.mywork.newsletter.controller;

import com.mywork.newsletter.dto.NewsLetterDTO;
import com.mywork.newsletter.service.NewsLetterServiceImpl;
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
public class NewsLetterController {

    @Autowired
    private NewsLetterServiceImpl service;
    public  NewsLetterController(){
        System.out.println("no-arg constructor of newsLetterController");
    }

    @GetMapping("index")
    public  String redirectToIndex(){
        System.out.println("redirect to index page");
        return "index";
    }

    @GetMapping("redirect")
    public String redirectToForm(){
        System.out.println("redirecting to Form page");
        return "form";
    }

    @PostMapping("submitNewsletter")
    public String send(NewsLetterDTO dto, Model model){
        System.out.println("send method is running...");

        System.out.println("dto: "+dto);

        if(service.valid(dto)){
            System.out.println("valid dto");

            model.addAttribute("dto", dto);
           // model.addAttribute("lName", dto.getLastName());

        }else {
            System.out.println("invalid dto");
            return "index";
        }
        List<NewsLetterDTO> newsLetterDTOList = service.getAll();
        model.addAttribute("allDto", newsLetterDTOList);
        return "allSubscription";
    }
    @GetMapping("getAll")
    public String getAll(Model model){
        System.out.println("get all method in NewsLetterController");
        List<NewsLetterDTO> newsLetterDTOList = service.getAll();
        model.addAttribute("allDto", newsLetterDTOList);

        return "allSubscription";
    }

    @GetMapping("getById")
    public  String getById(@RequestParam("newsId") Integer newsId, Model model){

        System.out.println("getById method is invoked..");
        NewsLetterDTO newsLetterDTO = service.getById(newsId);

        model.addAttribute("dto", newsLetterDTO);
        System.out.println("id is "+ newsId);

        return "byId";
    }
}