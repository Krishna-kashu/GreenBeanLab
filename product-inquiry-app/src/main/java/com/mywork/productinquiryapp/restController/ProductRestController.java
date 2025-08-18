package com.mywork.productinquiryapp.restController;

import com.mywork.productinquiryapp.dto.ProductDTO;
import com.mywork.productinquiryapp.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ProductRestController {

    @Autowired
    private ProductServiceImpl service;

    public ProductRestController() {
        System.out.println("no-arg constructor of ProductRestController");
    }

    @GetMapping("/mailCheck")
    public  String loginMailExists(@RequestParam("mail") String email){
        System.out.println("calling rest ");
        System.out.println("emaill:"+email);
        boolean result = service.checkMail(email);

        if(result){
            return "Email already exists";
        }
        return "Email not exists";
    }
    @GetMapping("findByID")
    public  String getById(@RequestParam("productId") Integer productId, Model model){

        System.out.println("getById method is invoked..");
        ProductDTO productDTO = service.getById(productId);

        model.addAttribute("dto", productDTO);
        System.out.println("id is "+ productId);

        return "id found";
    }
}
//http://localhost:8083/product-inquiry-app/findByID?productId=1
