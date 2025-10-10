package com.mywork.dairy360.restController;

import com.mywork.dairy360.service.MilkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/milk")
public class MilkServiceController {

    @Autowired
    private MilkService milkService;

    @GetMapping("/checkPhone/{phoneNumber}")
    public String checkPhoneNumber(@PathVariable String phoneNumber) {
        boolean exists = milkService.isPhoneNumberExist(phoneNumber);
        return exists ? "exist" : "available";
    }
}