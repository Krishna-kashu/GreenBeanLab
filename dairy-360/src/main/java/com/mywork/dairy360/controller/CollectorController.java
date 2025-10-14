package com.mywork.dairy360.controller;

import com.mywork.dairy360.dto.CollectMilkDTO;
import com.mywork.dairy360.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CollectorController {

    @Autowired
    private MilkServiceImpl milkService;

    @Autowired
    private SellerServiceImpl sellerService;

    @Autowired
    private AdminServiceImpl adminService;

    @GetMapping("/collectMilk")
    public String showCollectMilkPage(Model model) {
        System.out.println("showCollectMilkPage method in CollectorController");
        model.addAttribute("milkDTO", new CollectMilkDTO());

        model.addAttribute("sellers", sellerService.getAllActiveSellers());
        model.addAttribute("admins", adminService.getAllAdmins());
        List<String> milkTypes = new ArrayList<>();
        milkTypes.add("Cow");
        milkTypes.add("Buffalo");
        milkTypes.add("Mixed");
        model.addAttribute("milkTypes", milkTypes);

        return "collectMilk";
    }

    @PostMapping("/collectMilk")
    public String collectMilk(@Valid @ModelAttribute("milkDTO") CollectMilkDTO milkDTO,
                              BindingResult result,
                              Model model) {

        System.out.println("collectMilk method in CollectController");
        if (result.hasErrors()) {
            model.addAttribute("errorMsg", "Please correct the form errors!");
            return "collectMilk";
        }

        boolean saved = milkService.validateAndSave(milkDTO);
        if (saved) {
            model.addAttribute("successMsg", "Milk collection recorded successfully!");
            return "collectMilkSuccess";
        } else {
            model.addAttribute("errorMsg", "Unable to record milk details. Try again.");
            return "collectMilkError";
        }
    }
}
