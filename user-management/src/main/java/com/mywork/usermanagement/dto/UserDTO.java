package com.mywork.usermanagement.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private Integer userId;

    private String userName;

    private String gender;

    private Integer age;

    private Long phoneNumber;

    private String  email;

    private String address;

    private String bio;

    private List<String> roles;
}
