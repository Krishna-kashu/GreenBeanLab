package com.mywork.onlinelearning.dto;

import lombok.Data;

@Data
public class LearnerDTO {

    private Integer learnerId;

    private String name;

    private String gender;

    private String dob;

    private String email;

    private Long phone;

    private String state;

    private String address;

    private String otp;

    private Integer resetFlag;
}
