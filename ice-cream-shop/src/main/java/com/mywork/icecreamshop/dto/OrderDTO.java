package com.mywork.icecreamshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    @Size(min = 3, max = 30, message = "name size min = 3, max = 30")
    private String name;

    @Size(min = 3, max = 30, message = "flavour size min = 3, max = 30")
    private String flavour;

    @Min(value = 2, message = "minimum is 2")
    @Max(value = 50, message = "maximum is 50")
    private int quantity;
    private boolean takeAway;
    private boolean addOns;
    private String coupon;
    private MultipartFile multipartFile;
}