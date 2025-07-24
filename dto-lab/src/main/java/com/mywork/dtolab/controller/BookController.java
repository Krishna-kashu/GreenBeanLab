package com.mywork.dtolab.controller;

import com.mywork.dtolab.dto.BookDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BookController {

    public BookController() {
        System.out.println("BookController created");
    }

    @RequestMapping("/submitBook")
    public String submit(BookDTO bookDTO, Model model) {
        System.out.println("Submitted Book: " + bookDTO);
        model.addAttribute("book", bookDTO);
        return "result.jsp";
    }
}
