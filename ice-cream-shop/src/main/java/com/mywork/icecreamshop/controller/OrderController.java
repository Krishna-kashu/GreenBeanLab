package com.mywork.icecreamshop.controller;

import com.mywork.icecreamshop.dto.OrderDTO;
import com.mywork.icecreamshop.service.EmailSenderImpl;
import com.mywork.icecreamshop.service.IceCreamServiceImpl;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
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

    @Autowired
    private JavaMailSender javaMailSender;

    public OrderController(IceCreamServiceImpl iceCreamService){
        this.iceCreamService = iceCreamService;
        System.out.println("OrderController constructor with arguments");
    }



    //sending order details to mail without total price and gif file
   /* @RequestMapping("order")
    public  String onOrder(Model model, @Valid OrderDTO orderDTO, BindingResult bindingResult) throws IOException {
        System.out.println("onOrder method");
        System.out.println("dto: " + orderDTO);
        String emailBody = "New Ice Cream Order Received:\n"
                + "Name: " + orderDTO.getName() + "\n"
                + "Flavour: " + orderDTO.getFlavour() + "\n"
                + "Quantity: " + orderDTO.getQuantity() + "\n"
                + "Take Away: " + (orderDTO.isTakeAway() ? "Yes" : "No") + "\n"
                + "Add Ons: " + (orderDTO.isAddOns() ? "Yes" : "No") + "\n"
                + "Coupon Code: " + orderDTO.getCoupon() + "\n";


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
            emailSender.mailSend("sonalacharya911@gmail.com", "Thanks for your Ice Cream Order", emailBody);
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
    }*/

//    @RequestMapping("send")
//    public void sendMail(){
//        System.out.println("sendMail method");
//    }


    @RequestMapping("order")
    public  String onOrder(Model model, @Valid OrderDTO orderDTO, BindingResult bindingResult) throws IOException, MessagingException {
        System.out.println("onOrder method");
        System.out.println("dto: " + orderDTO);
        String filename = orderDTO.getMultipartFile().getOriginalFilename();

        byte[] bytes = orderDTO.getMultipartFile().getBytes();
        Path path = Paths.get("D:\\MyFilesJava\\" + orderDTO.getName() + System.currentTimeMillis());
        Files.write(path, bytes);

        String fileName = path.getFileName().toString();
        orderDTO.setName(fileName);

        if (bindingResult.hasErrors()) {

            bindingResult.getFieldErrors().forEach(error -> System.out.println(error.getField()
                    + " : " + error.getDefaultMessage()));

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

            String emailBody = "New Ice Cream Order Received:\n"
                    + "Name: " + orderDTO.getName() + "\n"
                    + "Flavour: " + orderDTO.getFlavour() + "\n"
                    + "Quantity: " + orderDTO.getQuantity() + "\n"
                    + "Take Away: " + (orderDTO.isTakeAway() ? "Yes" : "No") + "\n"
                    + "Add Ons: " + (orderDTO.isAddOns() ? "Yes" : "No") + "\n"
                    + "Coupon Code: " + orderDTO.getCoupon() + "\n"
                    + "    Total Price: ₹" + price;

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo("krishnavenin8192@gmail.com");
            helper.setSubject("Thanks for your Ice Cream Order");
            helper.setText(emailBody);

            //to attach a gif file
            File gifFile = new File("D:\\MyFilesJava\\thankyou.gif");
            if(gifFile.exists()){
                helper.addAttachment("thankyou.gif", gifFile);
            }
            javaMailSender.send(mimeMessage);
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
}