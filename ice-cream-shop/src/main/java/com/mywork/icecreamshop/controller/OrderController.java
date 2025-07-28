package com.mywork.icecreamshop.controller;

import com.mywork.icecreamshop.dto.OrderDTO;
import com.mywork.icecreamshop.service.IceCreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {

    private final IceCreamService iceCreamService;

    @Autowired
    public OrderController(IceCreamService iceCreamService){

        this.iceCreamService = iceCreamService;

        System.out.println("OrderController constructor with arguments");
    }
    @RequestMapping("/order")
    public  String onOrder(OrderDTO orderDTO, Model model){
        model.addAttribute("name", orderDTO.getName());

        double totalAmount = iceCreamService.totalPrice(orderDTO);
        model.addAttribute("total", totalAmount);
        System.out.println("ORDER submitted");
        return "success.jsp";
    }
}
