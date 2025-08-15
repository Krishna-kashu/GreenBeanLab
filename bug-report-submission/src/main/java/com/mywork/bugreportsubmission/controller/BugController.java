package com.mywork.bugreportsubmission.controller;

import com.mywork.bugreportsubmission.dto.BugDTO;
import com.mywork.bugreportsubmission.service.BugServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class BugController {

    @Autowired
    private BugServiceImpl service;

    public BugController(){
        System.out.println("\nno arg constructor of BugController ");
    }

    @GetMapping("index")
    public  String redirectToIndex(){
        System.out.println("redirect to index page");
        return "index";
    }

    @GetMapping("bugForm")
    public String redirectToForm(){
        System.out.println("\nredirecting to bugForm ");
        return "form";
    }

    @PostMapping("submitBug")
    public String submit(BugDTO dto, Model model){
        System.out.println("\n submit method is running..");
        System.out.println("dto: "+dto);

        if(service.validate(dto)){
            model.addAttribute("dto", dto);
        }else {
            System.out.println("invalid Bug dto");
            return "index";
        }

        List<BugDTO> bugList = service.getAll();
        model.addAttribute("bugs", bugList);
        return "listOfBug";
    }

    @GetMapping("getAll")
    public String getAll(Model model){
        System.out.println("get all method");
        List<BugDTO> bugList = service.getAll();
        model.addAttribute("bugs", bugList);
        return "listOfBug";
    }

    @GetMapping("gettingById")
    public  String getById(@RequestParam("bugId") Integer bugId, Model model){

        System.out.println("getById method is invoked..");
        BugDTO bugDTO = service.getById(bugId);

        model.addAttribute("dto", bugDTO);
        System.out.println("id is "+bugId);

        return "byId";
    }

    @GetMapping("update")
    public String edit(@RequestParam("id") Integer id, Model model){

        System.out.println("updateAll method in controller");
        System.out.println("id: "+id);
        BugDTO dto = service.getById(id);
        System.out.println("dto in update controller: "+dto);
        model.addAttribute("msg", "updated");
        model.addAttribute("dto", dto);

        return "editBugEntity";
    }

    @PostMapping("editBug")
    public String bugEdit( BugDTO dto, Model model){
        System.out.println("bugEdit method in controller");

        String isUpdated = service.updateBugDTO(dto);
        System.out.println("updated: "+isUpdated);

        List<BugDTO> bugList = service.getAll();
        model.addAttribute("bugs", bugList);
        return "listOfBug";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model){

        System.out.println("delete method in controller");

        String isDeleted = service.deleteById(id);
        System.out.println("deleted "+ isDeleted);

        model.addAttribute("msg", "delete");
        List<BugDTO> bugList = service.getAll();
        model.addAttribute("bugs", bugList);

        return "redirect:/getAll";
    }

    @GetMapping("/search")
    public String search(@RequestParam("reporterName") String reporterName, Model model){

        System.out.println("Search for reporterName: "+reporterName);
        List<BugDTO> bugs = service.searchByReporterName(reporterName);

        if(!bugs.isEmpty()){
            model.addAttribute("bugs", bugs);
            model.addAttribute("msg","Search results for: " + reporterName);
        }else {
            model.addAttribute("msg", "reporter name "+reporterName+" not exists");
        }

        return "listOfBug";
    }
}