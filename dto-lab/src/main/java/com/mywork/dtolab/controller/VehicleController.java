package com.mywork.dtolab.controller;

import com.mywork.dtolab.dto.VehicleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class VehicleController {

    public VehicleController() {
        System.out.println("No-arg constructor of VehicleController");
    }

    @RequestMapping("/submitVehicle")
    public String onSubmit(VehicleDTO vehicleDTO,
            Model model) {

        System.out.println("onSubmit VehicleController");

        model.addAttribute("vehicleDTO", vehicleDTO);

        System.out.println("Vehicle Details: " + vehicleDTO);

        return "result.jsp";
    }
}
