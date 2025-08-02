package com.mywork.icecreamshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor

public class OrderDTO {
    @Size(min = 3, max = 30)
    private String name;

    @Size(min = 3, max = 30)
    private String flavour;

    @Min(value = 2, message = "minimum is one")
    @Max(value = 50, message = "minimum is one")
    private int quantity;
    private boolean takeAway;
    private boolean addOns;
    private String coupon;
    private MultipartFile multipartFile;
}