package com.mywork.usermanagement.controller;

import com.mywork.usermanagement.dto.UserDTO;

import com.mywork.usermanagement.service.UserServiceImpl;
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
public class UserController {

    @Autowired
    UserServiceImpl service;


    public UserController(){
        System.out.println("no-arg constructor of UserController");
    }

    @GetMapping("index")
    public String redirectToIndex(){
        System.out.println("redirecting to index");
        return "index";
    }

    @GetMapping("form")
    public String redirectToForm() {
        System.out.println("redirecting to form");
        return "form";
    }

    @PostMapping("save")
    public String submit(UserDTO dto, Model model){
        System.out.println("submit method in controller");

        if(service.save(dto)){
            model.addAttribute("dto", dto);
            model.addAttribute("name", dto.getUserName());
        }else {
            System.out.println("invalid DTO");
            return "index";
        }
        return "success";
    }

    @GetMapping("getAll")
    public String fetchAll(Model model){
        System.out.println("fetchAll method in controller");
        List<UserDTO> userDTOList = service.getAll();
        model.addAttribute("user", userDTOList);

        return "allUser";
    }

    @GetMapping("getById")
    public String getById(@RequestParam("userId") Integer userId, Model model){
        System.out.println("getById method is invoking");

        UserDTO userDTO = service.getById(userId);

        model.addAttribute("user", userDTO);
        model.addAttribute("id", userDTO.getUserId());
        System.out.println("id : "+userId);

        return "idPage";
    }
}