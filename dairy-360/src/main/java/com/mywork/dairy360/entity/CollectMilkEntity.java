
package com.mywork.dairy360.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "collect_milk_details")
public class CollectMilkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collect_milk_id")
    private Integer collectMilkId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private SellerEntity seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private AdminEntity admin;

    @Column(name = "type_of_milk")
    private String typeOfMilk;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private Float quantity;

    @Column(name = "collected_date")
    private LocalDate collectedDate;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "collectMilkEntity")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private CollectMilkAuditEntity collectMilkAuditEntity;
}
