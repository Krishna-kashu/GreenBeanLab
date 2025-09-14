package com.mywork.dairy360.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ByProductDTO {
    private Integer byProductId;
    private String productType;
    private Long milkBatchId;
    private Double quantity;
    private LocalDate expiryDate;
    private Double price;
    private String status;
}