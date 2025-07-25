package com.mywork.dtolab.dto;

import lombok.Data;

@Data
public class EmployeeDTO {
    private String name;
    private String department;
    private String employeeId;
    private String designation;
    private double salary;
    private boolean isPermanent;
}
