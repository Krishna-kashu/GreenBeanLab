package com.mywork.dairy360.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product_info")
@NamedQuery(name = "getAllActiveProducts", query = "SELECT p FROM ProductEntity p WHERE p.active = true ORDER BY p.productId DESC")
@NamedQuery(name = "getCount", query = "SELECT COUNT(p) FROM ProductEntity p WHERE p.active = true")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private Double price;

    @Column(name = "type")
    private String type;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    private String createdOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on")
    private String updatedOn;

    @Column(name = "is_active")
    private Boolean active;
}
