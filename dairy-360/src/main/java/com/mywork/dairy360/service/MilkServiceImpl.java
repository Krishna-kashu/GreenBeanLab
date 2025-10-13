package com.mywork.dairy360.service;

import com.mywork.dairy360.dto.CollectMilkDTO;
import com.mywork.dairy360.entity.CollectMilkAuditEntity;
import com.mywork.dairy360.entity.CollectMilkEntity;
import com.mywork.dairy360.repo.MilkRepoImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MilkServiceImpl implements MilkService {

    @Autowired
    private MilkRepoImpl repo;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private AdminService adminService;

    @Override
    public boolean validateAndSave(CollectMilkDTO dto) {
        System.out.println("validateAndSave method in Service");
        if (dto == null) return false;

        if (repo.findByPhoneNumber(dto.getPhoneNumber()) != null) return false;

        CollectMilkEntity entity = new CollectMilkEntity();

        entity.setSeller(sellerService.findEntityById(dto.getSeller()));
        entity.setAdmin(adminService.findEntityById(dto.getAdmin()));
        entity.setTypeOfMilk(dto.getTypeOfMilk());
        entity.setPrice(dto.getPrice());
        entity.setQuantity(dto.getQuantity());
        entity.setCollectedDate(dto.getCollectedDate());

        CollectMilkAuditEntity audit = new CollectMilkAuditEntity();
        audit.setCreatedAt(LocalDateTime.now());
        audit.setCreatedBy(entity.getAdmin().getAdminName());
        audit.setCollectMilkEntity(entity);
        entity.setCollectMilkAuditEntity(audit);

        return repo.save(entity);
    }

    @Override
    public boolean isPhoneNumberExist(String phoneNumber) {
        return repo.findByPhoneNumber(phoneNumber) != null;
    }
}