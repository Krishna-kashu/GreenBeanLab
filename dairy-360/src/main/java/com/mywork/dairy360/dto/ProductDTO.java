package com.mywork.dairy360.dto;

import lombok.Data;

@Data
public class ProductDTO {

    private Integer productId;

    private String productName;

    private Double price;

    private String type;

    private String createdBy;

    private String createdOn;

    private String updatedBy;

    private String updatedOn;

    private Boolean active = true;
}
