package com.mywork.passport_seva.dto;

import lombok.Data;

@Data
public class PassportDTO {

    private Integer id;

    private String passportOffice;

    private String givenName;

    private  String surName;

    private String dob;

    private String email;

    private Long phone;

    private boolean sameLoginId;

    private String loginId;

    private String password;

    private String confirmPassword;

    private String hintQuestion;

    private String hintAnswer;
}
