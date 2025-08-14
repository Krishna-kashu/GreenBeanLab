package com.mywork.shop_app.dto;


import lombok.Data;

@Data
public class ShopDTO {

    private Integer shopId;

    private String shopName;

    private String shopOwner;

    private Integer totalBranch;

    private String ShopType;

    private String email;

    private Long contact;
}
