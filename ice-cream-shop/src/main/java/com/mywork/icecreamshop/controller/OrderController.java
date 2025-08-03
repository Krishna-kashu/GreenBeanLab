package com.mywork.icecreamshop.controller;

import com.mywork.icecreamshop.dto.OrderDTO;
import com.mywork.icecreamshop.service.IceCreamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class OrderController {

    @Autowired
    private final IceCreamServiceImpl iceCreamService;

    public OrderController(IceCreamServiceImpl iceCreamService){

        this.iceCreamService = iceCreamService;

        System.out.println("OrderController constructor with arguments");
    }

    @RequestMapping("/order")
    public  String onOrder(Model model, @Valid OrderDTO orderDTO, BindingResult bindingResult) throws IOException {
        System.out.println("onOrder method");
        System.out.println("dto: " + orderDTO);

        String filename = orderDTO.getMultipartFile().getOriginalFilename();

        byte[] bytes = orderDTO.getMultipartFile().getBytes();
        Path path = Paths.get("D:\\MyFilesJava\\" + orderDTO.getName() + System.currentTimeMillis());
        Files.write(path, bytes);

        if (bindingResult.hasErrors()) {

            bindingResult.getFieldErrors().forEach(error -> System.out.println(error.getField() + " : " + error.getDefaultMessage()));

            System.out.println("Invalid Details");
            model.addAttribute("dto", orderDTO);
            model.addAttribute("message", "Invalid details");
            return "orderForm";
        }

        if (iceCreamService.validate(orderDTO)) {
            model.addAttribute("name", orderDTO.getName());
            double price = iceCreamService.totalPrice(orderDTO);
            model.addAttribute("price", price);
            return "success";
        } else {
            System.out.println("Invalid Details");
            model.addAttribute("dto", orderDTO);
            model.addAttribute("message", "Invalid details");
            return "Order";
        }
    }
}
