package com.mywork.productinquiryapp.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "product_inquiry_table")
@NamedQuery(name = "fetchAll", query = "select p from ProductEntity p")
@NamedQuery(name = "checkMail", query = "select p from ProductEntity p where p.email=:email")
@NamedQuery(name = "deleteById", query = "delete from ProductEntity p where p.id=:id")
@NamedQuery(name = "updateEntity", query = "update ProductEntity p set p.fullName=:fullName, " +
        "p.email=:email, p.phone=:phone, p.productName=:productName, p.inquiryType=:inquiryType, " +
        "p.message=:message where p.id=:id")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private Long phone;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "inquiry_type")
    private String inquiryType;

    @Column(name = "message")
    private String message;
}
