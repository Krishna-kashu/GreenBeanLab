package com.mywork.passport_seva.controller;

import com.mywork.passport_seva.dto.PassportDTO;
import com.mywork.passport_seva.service.PassportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class PassportController {

    @Autowired
    PassportServiceImpl service;


    public PassportController(){
        System.out.println("no-arg constructor of PassportController");
    }

    @GetMapping("index")
    public String redirect(){
        System.out.println("redirectToIndex");
        return "index";
    }

    @GetMapping("form")
    public String redirectToForm(){
        System.out.println("redirecting to Form page");
        return "formPage";
    }

    @PostMapping("submit")
    public String submit(PassportDTO dto, Model model){
        System.out.println("submit method in controller");
        System.out.println("saved dto: "+dto);

        if(service.save(dto)){
            System.out.println("valid dto");
            model.addAttribute("dto", dto);
        }else {
            System.out.println("not valid");
            return "index";
        }

        List<PassportDTO> passportDTOS = service.getAll();
        model.addAttribute("dtos", passportDTOS);
        return "getAll";
    }

    @GetMapping("allDetails")
    public String  getAll(Model model){

        System.out.println("invoking getAll in Controller");
        List<PassportDTO> passportDTOS = service.getAll();
        model.addAttribute("dtos", passportDTOS);
        return "getAll";
    }

    @GetMapping("getById")
    public  String getById(@RequestParam("id") Integer id, Model model){

        System.out.println("getById method is invoked..");
        PassportDTO dto = service.getById(id);

        model.addAttribute("dto", dto);
        model.addAttribute("id", id);
        System.out.println("id is "+id);

        return "entityById";
    }

    @GetMapping("update")
    public String edit(@RequestParam("id") Integer id, Model model){

        System.out.println("updateAll method in controller");
        System.out.println("id: "+id);
        PassportDTO dto = service.getById(id);
        System.out.println("dto in update controller: "+dto);
        model.addAttribute("msg", "updated");
        model.addAttribute("dto", dto);

        return "edit";
    }

    @PostMapping("editPassport")
    public String bugEdit( PassportDTO dto, Model model){
        System.out.println("bugEdit method in controller");

        String isUpdated = service.updateEntity(dto);
        System.out.println("updated: "+isUpdated);

        List<PassportDTO> passportDTOList = service.getAll();
        model.addAttribute("dtos", passportDTOList);
        return "getAll";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model){

        System.out.println("delete method in controller");

        String isDeleted = service.deleteById(id);
        System.out.println("deleted "+ isDeleted);

        model.addAttribute("msg", "delete");
        List<PassportDTO> passportDTOS = service.getAll();
        model.addAttribute("bugs", passportDTOS);

        return "redirect:/allDetails";
    }
}
