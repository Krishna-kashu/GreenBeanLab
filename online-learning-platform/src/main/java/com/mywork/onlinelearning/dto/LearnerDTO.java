package com.mywork.onlinelearning.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
public class LearnerDTO {

    private Integer learnerId;

    private String name;

    private String gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    private String email;

    private Long phone;

    private String state;

    private String password;

    private String profileImage;

    private String address;

    private Boolean isActive;

    private Integer loginCount;

    private String city;

    private String pincode;
}
