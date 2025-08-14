package com.mywork.shop_app.controller;

import com.mywork.shop_app.dto.ShopDTO;
import com.mywork.shop_app.service.ShopServiceImpl;
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
public class ShopController {

    @Autowired
    private ShopServiceImpl service;
    public  ShopController(){
        System.out.println("no-arg constructor of ShopController");
    }
    @GetMapping("index")
    public  String redirectToIndex(){
        System.out.println("redirect to index page");
        return "index";
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
        List<ShopDTO> shopDTOS = service.getAll();
        model.addAttribute("shops", shopDTOS);
        return "allShops";
        //return "shopForm";
    }

    @GetMapping("fetchAll")
    public String getAll(Model model){
        System.out.println("get all method");
        List<ShopDTO> shopDTOS = service.getAll();
        model.addAttribute("shops", shopDTOS);
        return "allShops";
    }

    @GetMapping("fetchId")
    public  String getById(@RequestParam("shopId") Integer shopId, Model model){

        System.out.println("getById method is invoked..");
        ShopDTO shopDTO = service.getById(shopId);

        model.addAttribute("dto", shopDTO);
        System.out.println("id is "+ shopId);

        return "getById";
    }
}
