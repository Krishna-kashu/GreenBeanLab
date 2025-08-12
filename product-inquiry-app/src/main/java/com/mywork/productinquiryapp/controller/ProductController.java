package com.mywork.productinquiryapp.controller;

import com.mywork.productinquiryapp.dto.ProductDTO;
import com.mywork.productinquiryapp.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductServiceImpl service;

    public ProductController(){
        System.out.println("\nno arg constructor of ProductController ");
    }

    @GetMapping("index")
    public  String redirectToIndex(){
        System.out.println("redirect to index page");
        return "index";
    }

    @GetMapping("inquiryForm")
    public String redirectToForm(){
        System.out.println("\nredirecting to inquiryForm ");
        return "form";
    }

    @PostMapping("submitInquiry")
    public String submit(ProductDTO dto, Model model){
        System.out.println("\n submit method is running..");
        System.out.println("dto: "+dto);

        if(service.valid(dto)){

            model.addAttribute("dto", dto);
            model.addAttribute("name", dto.getFullName());

        }else {
            System.out.println("invalid dto");
            return "index";
        }
        List<ProductDTO> productDTOS = service.getAll();
        model.addAttribute("products", productDTOS);

        return "allProduct";
//        return "success";
    }

    @GetMapping("get")
    private String fetchAll(Model model){
        System.out.println("\n fetchAll method in controller");

        List<ProductDTO> productDTOS = service.getAll();
        model.addAttribute("products", productDTOS);

        return "allProduct";
    }

}
