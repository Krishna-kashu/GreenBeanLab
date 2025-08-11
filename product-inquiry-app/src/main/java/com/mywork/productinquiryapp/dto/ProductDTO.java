package com.mywork.productinquiryapp.dto;

import lombok.Data;

@Data
public class ProductDTO {

    private  Integer id;

    private String fullName;

    private String email;

    private Long phone;

    private String productName;

    private String inquiryType;

    private String message;
}
