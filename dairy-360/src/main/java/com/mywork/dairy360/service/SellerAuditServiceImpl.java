package com.mywork.dairy360.service;

import com.mywork.dairy360.dto.SellerAuditDTO;
import com.mywork.dairy360.entity.SellerAuditEntity;
import com.mywork.dairy360.repo.SellerAuditRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellerAuditServiceImpl implements SellerAuditService {

    @Autowired
    private SellerAuditRepo sellerAuditRepo;

    @Override
    public boolean saveAudit(SellerAuditDTO auditDto) {
        try {

            SellerAuditEntity entity = sellerAuditRepo.findBySellerId(auditDto.getSellerId());

            if (entity == null) {
                entity.setCreatedBy(auditDto.getCreatedBy());
                entity.setCreatedOn(auditDto.getCreatedOn());
                }
            entity.setSellerId(auditDto.getSellerId());
            entity.setSellerName(auditDto.getSellerName());
            entity.setUpdatedBy(auditDto.getUpdatedBy());
            entity.setUpdatedOn(auditDto.getUpdatedOn());
            entity.setAdminName(auditDto.getAdminName());

            System.out.println("saved in repo");
            return sellerAuditRepo.save(entity);
        } catch (Exception e) {
            System.out.println("Error saving seller audit DTO: " + e.getMessage());
            return false;
        }
    }

    @Override
    public SellerAuditDTO getAuditBySellerId(Integer sellerId) {
        SellerAuditEntity entity = sellerAuditRepo.findBySellerId(sellerId);
        if (entity == null) return null;

        SellerAuditDTO dto = new SellerAuditDTO();
        BeanUtils.copyProperties(entity,dto);
        if (entity.getAdmin() != null) {
            dto.setAdminName(entity.getAdmin().getAdminName());
        }
        return dto;
    }

    @Override
    public List<SellerAuditDTO> getAllAudits() {
        return sellerAuditRepo.findAll().stream().map(entity -> {
            SellerAuditDTO dto = new SellerAuditDTO();
            BeanUtils.copyProperties(entity,dto);

            if (entity.getAdmin() != null) {
                dto.setAdminName(entity.getAdmin().getAdminName());
            }

            return dto;
        }).collect(Collectors.toList());
    }
}
