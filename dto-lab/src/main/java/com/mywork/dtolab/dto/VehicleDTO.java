package com.mywork.dtolab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleDTO {
    private  String model;
    private String color;
    private String vin;
    private boolean isElectric;
    private String type;
    private int year;
}
