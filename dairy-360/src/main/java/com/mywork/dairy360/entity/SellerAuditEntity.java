package com.mywork.dairy360.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "seller_audit")
@NamedQuery(name = "findAllAudits", query = "SELECT a FROM SellerAuditEntity a ORDER BY a.createdOn DESC")
@NamedQuery(name = "findBySellerId", query = "SELECT s FROM SellerAuditEntity s WHERE s.sellerId = :sellerId")
public class SellerAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_audit_id")
    private Integer sellerAuditId;

    @Column(name = "seller_id")
    private Integer sellerId;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private AdminEntity admin;

    @Column(name = "admin_name")
    private String adminName;

    @Column(name = "seller_name")
    private String sellerName;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;
}
