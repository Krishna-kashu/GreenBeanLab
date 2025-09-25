package com.mywork.dairy360.service;

import com.mywork.dairy360.dto.SellerAuditDTO;

import java.util.List;

public interface SellerAuditService {
    boolean saveAudit(SellerAuditDTO auditDto);

    SellerAuditDTO getAuditBySellerId(Integer sellerId);

    List<SellerAuditDTO> getAllAudits();
}