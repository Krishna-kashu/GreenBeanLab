package com.mywork.bugreportsubmission.dto;

import lombok.Data;

@Data
public class BugDTO {

    private  Integer id;

    private String reporterName;

    private String email;

    private String title;

    private String description;

    private Integer stepsCount;

    private Boolean isCritical;

}