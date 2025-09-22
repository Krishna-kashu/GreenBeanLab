package com.mywork.dairy360.restController;

import com.mywork.dairy360.dto.AdminDTO;
import com.mywork.dairy360.service.AdminServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    @Autowired
    private AdminServiceImpl adminService;

    @PostMapping("/save")
    @ApiOperation(value = "Save admin data")
    public ResponseEntity<String> saveAdminDetails(@Valid @RequestBody AdminDTO adminDTO, BindingResult bindingResult) {
        System.out.println("saveAdminDetails method in rest controller");
        if (bindingResult.hasErrors()) {
            System.out.println("Error in fields");
            bindingResult.getFieldErrors()
                    .forEach(fieldError -> System.out.println(fieldError.getField() + "-> " + fieldError.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errors in fields");
        }
        if (adminService.save(adminDTO)) {
            return ResponseEntity.ok("Details saved");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid details");
        }
    }

    @GetMapping("/mailCheck")
    public ResponseEntity<String> checkMail(@RequestParam("email") String email) {
        System.out.println("checkMail method in admin restController : " + email);
        String mail = adminService.checkMail(email);

        if (mail == null) {
            return ResponseEntity.ok("Available");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email Registered");
        }
    }
}