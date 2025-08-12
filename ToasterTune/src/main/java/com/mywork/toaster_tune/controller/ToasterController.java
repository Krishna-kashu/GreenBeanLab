package com.mywork.toaster_tune.controller;

import com.mywork.toaster_tune.dto.ToasterDTO;
import com.mywork.toaster_tune.service.ToasterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ToasterController {

    @Autowired
    private final ToasterServiceImpl toasterService;

    public ToasterController(ToasterServiceImpl toasterService) {
        this.toasterService = toasterService;
        System.out.println("ToasterController constructor with arguments");
    }

    @RequestMapping("/toaster")
    public String onRegister(Model model, @Valid ToasterDTO toasterDTO, BindingResult bindingResult) throws IOException {
        System.out.println("onRegister method");
        System.out.println("dto: " + toasterDTO);

        byte[] bytes = toasterDTO.getMultipartFile().getBytes();
        Path path = Paths.get("D:\\MyFilesJava\\" + toasterDTO.getModelName() + System.currentTimeMillis());
        Files.write(path, bytes);

        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(error ->
                    System.out.println(error.getField() + " : " + error.getDefaultMessage())
            );
            model.addAttribute("dto", toasterDTO);
            model.addAttribute("message", "Invalid details");
            return "toaster";
        }

        if (toasterService.validate(toasterDTO)) {
            System.out.println("validation passed");
            model.addAttribute("modelName", toasterDTO.getModelName());
            double price = toasterService.totalPrice(toasterDTO);
            model.addAttribute("price", price);
            return "success";
        } else {
            model.addAttribute("dto", toasterDTO);
            model.addAttribute("message", "Invalid details");
            return "toaster";
        }
    }
}
