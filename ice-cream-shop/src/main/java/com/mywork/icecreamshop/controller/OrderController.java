package com.mywork.icecreamshop.controller;

import com.mywork.icecreamshop.dto.OrderDTO;
import com.mywork.icecreamshop.service.EmailSenderImpl;
import com.mywork.icecreamshop.service.IceCreamServiceImpl;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
//@RequestMapping("/")
public class OrderController {

    @Autowired
    private final IceCreamServiceImpl iceCreamService;
    @Autowired
    private  EmailSenderImpl emailSender;

    public OrderController(IceCreamServiceImpl iceCreamService){
        this.iceCreamService = iceCreamService;
        System.out.println("OrderController constructor with arguments");
    }

//    @GetMapping("sendEmail")
//    public String mailSender(){
//        emailSender.mailSend("sonalacharya911@gmail.com","1115");
//        return null;
//    }

    @RequestMapping("order")
    public  String onOrder(Model model, @Valid OrderDTO orderDTO, BindingResult bindingResult) throws IOException {
        System.out.println("onOrder method");
        System.out.println("dto: " + orderDTO);
        emailSender.mailSend("krishnavenin8192@gmail.com","5555");

        String filename = orderDTO.getMultipartFile().getOriginalFilename();

        byte[] bytes = orderDTO.getMultipartFile().getBytes();
        Path path = Paths.get("D:\\MyFilesJava\\" + orderDTO.getName() + System.currentTimeMillis());
        Files.write(path, bytes);

        String fileName = path.getFileName().toString();
        orderDTO.setName(fileName);

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
            model.addAttribute("file",fileName);
            return "success";
        } else {
            System.out.println("Invalid Details");
            model.addAttribute("dto", orderDTO);
            model.addAttribute("message", "Invalid details");
            return "Order";
        }
    }
    @GetMapping("download")
    public void download(HttpServletResponse response, @RequestParam("profile") String name) throws IOException {
        response.setContentType("image/jpg");
        File file = new File("D:\\MyFilesJava\\"+name);
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        ServletOutputStream out = response.getOutputStream();
        IOUtils.copy(in, out);
        response.flushBuffer();
    }

//    @RequestMapping("send")
//    public void sendMail(){
//        System.out.println("sendMail method");
//    }
}