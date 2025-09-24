package com.mywork.dairy360.dto;

import lombok.Data;

@Data
public class RegisterDTO {

    private Integer userId;

    private String name;

    private String email;

    private Long phone;

    private String password;

    private String role;

    private  Double walletBalance;

}