package com.mywork.dairy360.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
public class CollectMilkDTO {
    private Integer collectMilkId;

    private SellerDTO seller;

    private AdminDTO admin;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String typeOfMilk;

    @Positive
    @NotNull
    private Double price;

    @Positive
    @NotNull
    private Float quantity;

    @NotNull
    private LocalDate collectedDate = LocalDate.now();
}