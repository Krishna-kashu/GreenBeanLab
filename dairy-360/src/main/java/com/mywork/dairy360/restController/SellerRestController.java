package com.mywork.dairy360.restController;

import com.mywork.dairy360.service.SellerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SellerRestController {

    @Autowired
    private SellerServiceImpl sellerService;

    public SellerRestController(){
        System.out.println("seller rest controller ");
    }

    @GetMapping("adminMailCheck")
    public ResponseEntity<String> checkAdminMail(@RequestParam("email") String email) {
        System.out.println("checking admin mails at controller : "+email);

        String existingMail = sellerService.checkMail(email);
        System.out.println("email at controller: "+existingMail);
        if (existingMail == null) {

            return ResponseEntity.ok("Available");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email Registered");
        }
    }
}