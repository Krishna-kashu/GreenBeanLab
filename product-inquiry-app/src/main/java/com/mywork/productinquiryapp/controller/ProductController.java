package com.mywork.productinquiryapp.controller;

import com.mywork.productinquiryapp.dto.ProductDTO;
import com.mywork.productinquiryapp.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        //return "success";
    }

    @GetMapping("get")
    private String fetchAll(Model model){
        System.out.println("\n fetchAll method in controller");

        List<ProductDTO> productDTOS = service.getAll();
        model.addAttribute("products", productDTOS);

        return "allProduct";
    }

    @GetMapping("getById")
    public  String getById(@RequestParam("productId") Integer productId, Model model){

        System.out.println("getById method is invoked..");
        ProductDTO productDTO = service.getById(productId);

        model.addAttribute("dto", productDTO);
        System.out.println("id is "+ productId);

        return "idPage";
    }
    @GetMapping("edit")
    public  String edit(@RequestParam("id") Integer id, Model model){
        System.out.println("edit method in controller, id:"+id);
        ProductDTO dto = service.getById(id);
        System.out.println("dto in edit "+dto);
        model.addAttribute("msg", "updated");
        model.addAttribute("dto", dto);
        return "edit";
    }
    @PostMapping("update")
    public  String update(ProductDTO dto , Model model){
        System.out.println("update method in controller");
        String updated = service.updateDto(dto);
        System.out.println("updated: "+updated);

        List<ProductDTO> productDTOS = service.getAll();
        model.addAttribute("products", productDTOS);

        return "allProduct";
    }

    @GetMapping("delete/{id}")
    public  String delete(@PathVariable("id") Integer id){

        System.out.println("delete method in controller");

        String deleted = service.deleteDto(id);
        System.out.println("deleted "+deleted);

        return "redirect:/get";
    }


}
