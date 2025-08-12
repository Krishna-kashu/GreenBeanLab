package com.mywork.newsletter.dto;

import lombok.Data;

@Data
public class NewsLetterDTO {

    private  Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private Integer age;

    private String gender;

    private String topic;
}