package com.mywork.icecreamshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class OrderDTO {
    private String name;
    private String flavour;
    private int quantity;
    private boolean takeAway;
    private boolean addOns;
    private String coupon;
}