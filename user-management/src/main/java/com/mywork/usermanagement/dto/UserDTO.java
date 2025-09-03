package com.mywork.usermanagement.dto;

import lombok.Data;

@Data
public class UserDTO {

    private Integer userId;

    private String userName;

    private String gender;

    private Integer age;

    private Long phoneNumber;

    private String  email;

}
