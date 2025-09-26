package com.mywork.dairy360.dto;

import com.mywork.dairy360.entity.AdminEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SellerAuditDTO {
    private Integer sellerAuditId;
    private Integer sellerId;
    private AdminEntity admin;
    private String adminName;
    private String sellerName;
    private String createdBy;
    private LocalDateTime createdOn;
    private String updatedBy;
    private LocalDateTime updatedOn;
}