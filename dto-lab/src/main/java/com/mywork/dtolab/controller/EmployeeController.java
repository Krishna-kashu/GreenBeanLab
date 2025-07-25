package com.mywork.dtolab.controller;

import com.mywork.dtolab.dto.EmployeeDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class EmployeeController {

    public EmployeeController() {
        System.out.println("EmployeeController created");
    }

    @RequestMapping("/submitEmployee")
    public String submit(EmployeeDTO employeeDTO, Model model) {
        System.out.println("Employee submitted: " + employeeDTO);
        model.addAttribute("employeeDTO", employeeDTO);
        return "result.jsp";
    }
}