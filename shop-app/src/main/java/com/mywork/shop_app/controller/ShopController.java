package com.mywork.shop_app.controller;

import com.mywork.shop_app.dto.ShopDTO;
import com.mywork.shop_app.service.ShopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ShopController {

    @Autowired
    private ShopServiceImpl service;
    public  ShopController(){
        System.out.println("no-arg constructor of ShopController");
    }

    @GetMapping("redirectToForm")
    public String redirectToForm(){
        System.out.println("send method in redirectToForm");
        return "shopForm";
    }

    @PostMapping("submit")
    public String send(ShopDTO dto, Model model){
        System.out.println("send method in ShopController");
        System.out.println("Shop details: "+dto);

        if (service.valid(dto)) {
            System.out.println("valid dto");
            model.addAttribute("dto", dto);
            model.addAttribute("name", dto.getShopName());
            model.addAttribute("totalBranch", dto.getTotalBranch());
            model.addAttribute("owner", dto.getShopOwner());
            model.addAttribute("mail", dto.getEmail());
            model.addAttribute("contact", dto.getContact());

        } else {
            System.out.println("invalid dto");
            return "index";
        }

        return "shopForm";
    }

}
