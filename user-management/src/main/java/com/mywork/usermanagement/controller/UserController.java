package com.mywork.usermanagement.controller;

import com.mywork.usermanagement.dto.UserDTO;

import com.mywork.usermanagement.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
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
        List<UserDTO> userDTOList = service.getAll();
        model.addAttribute("user", userDTOList);

        return "allUser";
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

    @GetMapping("edit")
    public  String edit(@RequestParam("id") Integer id, Model model){
        System.out.println("edit method in controller");
        UserDTO dto = service.getById(id);
        System.out.println("dto in edit "+dto);
        model.addAttribute("dto", dto);

        return "edit";
    }

    @PostMapping("update")
    public  String update(UserDTO dto, Model model){
        System.out.println("update method in controller");
        String updated = service.updateDto(dto);
        System.out.println("updated: "+updated);
        List<UserDTO> userDTOList = service.getAll();
        model.addAttribute("user", userDTOList);

        return "allUser";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        System.out.println("delete method in controller");

        String deleted = service.deleteDto(id);
        System.out.println("deleted "+deleted);

        return "redirect:/getAll";
    }

}