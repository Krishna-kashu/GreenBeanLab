package com.mywork.shop_app.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "shop_details")
@NamedQuery(name = "findAll", query = "select s from ShopEntity s")
@NamedQuery(name = "emailExists", query = "select s from ShopEntity s where s.email = :email")


public class ShopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private Integer shopId;

    @Column(name = "shop_name")
    private String shopName;

    @Column(name = "shop_owner")
    private String shopOwner;

    @Column(name = "total_branch")
    private Integer totalBranch;

    @Column(name = "shop_type")
    private String ShopType;

    @Column(name = "email")
    private String email;

    @Column(name = "contact")
    private Long contact;
}
