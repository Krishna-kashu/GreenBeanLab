package com.mywork.dairy360.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "by_products")
public class ByProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "by_product_id")
    private Long byProductId;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "price")
    private Double price;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "milk_batch_id")
    private MilkCollectionEntity milkBatch;
}
