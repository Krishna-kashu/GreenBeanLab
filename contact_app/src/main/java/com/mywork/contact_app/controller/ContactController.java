package com.mywork.contact_app.controller;

import com.mywork.contact_app.dto.ContactDTO;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class ContactController {
    public ContactController(){
        System.out.println("ContactController created");
    }
    @RequestMapping("/")
    public String submit(ContactDTO contactDTO, Model model){
        System.out.println("Submitted"+ contactDTO);
        model.addAttribute("contact", contactDTO);
        return "result.jsp";
    }
}
