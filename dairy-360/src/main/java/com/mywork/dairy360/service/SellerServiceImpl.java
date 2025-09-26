package com.mywork.dairy360.service;

import com.mywork.dairy360.dto.AdminDTO;
import com.mywork.dairy360.dto.SellerDTO;
import com.mywork.dairy360.entity.AdminEntity;
import com.mywork.dairy360.entity.AuditEntity;
import com.mywork.dairy360.entity.SellerEntity;
import com.mywork.dairy360.entity.SellerAuditEntity;
import com.mywork.dairy360.repo.SellerAuditRepo;
import com.mywork.dairy360.repo.SellerRepoImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepoImpl sellerRepo;

    @Autowired
    private SellerAuditRepo sellerAuditRepo;

    @Override
    @Transactional
    public boolean saveSeller(SellerDTO sellerDto, AdminDTO adminDTO) {
        try {
            if (sellerDto.getSellerId() == null && sellerRepo.existsByEmail(sellerDto.getEmail())) {
                return false;
            }

            SellerEntity entity;
            boolean isNew = (sellerDto.getSellerId() == null);

            if (isNew) {
                entity = new SellerEntity();
            } else {
                entity = sellerRepo.findById(sellerDto.getSellerId());
                if (entity == null) return false;
            }

            entity.setFirstName(sellerDto.getFirstName());
            entity.setLastName(sellerDto.getLastName());
            entity.setPhone(sellerDto.getPhone());
            entity.setAddress(sellerDto.getAddress());
            entity.setMilkType(sellerDto.getMilkType());
            entity.setActive(true);

            if (isNew) {
                entity.setEmail(sellerDto.getEmail());
                sellerRepo.save(entity);
            } else {
                sellerRepo.update(entity);
            }
            SellerAuditEntity audit = sellerAuditRepo.findBySellerId(entity.getSellerId());

            AdminEntity adminEntity=new AdminEntity();
            BeanUtils.copyProperties(adminDTO,adminEntity);

            if (audit == null) {
                audit = new SellerAuditEntity();
                audit.setCreatedBy(adminDTO.getAdminName());
                audit.setCreatedOn(LocalDateTime.now());
                audit.setAdmin(adminEntity);
            }

            audit.setSellerId(entity.getSellerId());
            audit.setSellerName(entity.getFirstName() + " " + entity.getLastName());
            audit.setUpdatedBy(adminDTO.getAdminName());
            audit.setUpdatedOn(LocalDateTime.now());
            audit.setAdminName(adminDTO.getAdminName());

            sellerAuditRepo.save(audit);
            return true;

        } catch (Exception e) {
            System.out.println("Error saving seller: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<SellerDTO> getAllActiveSellers() {
        return sellerRepo.findAllActive().stream().map(entity -> {
            SellerDTO dto = new SellerDTO();
            BeanUtils.copyProperties(entity,dto);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public SellerDTO getSellerById(Integer id) {
        SellerEntity entity = sellerRepo.findById(id);
        if (entity == null) return null;

        SellerDTO dto = new SellerDTO();
        BeanUtils.copyProperties(entity,dto);
        return dto;
    }

    @Override
    public boolean softDeleteSeller(Integer id, String adminName) {
        try {
            SellerEntity entity = sellerRepo.findById(id);
            if (entity == null) return false;

            entity.setActive(false);
            boolean deleted = sellerRepo.update(entity);

            if (deleted) {
                SellerAuditEntity audit = sellerAuditRepo.findBySellerId(id);
                if (audit != null) {
                    audit.setUpdatedBy(adminName);
                    audit.setUpdatedOn(LocalDateTime.now());
                    sellerAuditRepo.save(audit);
                }
            }
            return deleted;
        } catch (Exception e) {
            System.out.println("Error deleting seller: " + e.getMessage());
            return false;
        }
    }

    @Override
    public String checkMail(String email){
        System.out.println("checkMail method in service for Ajax");
        return sellerRepo.checkMail(email);
    }

    //pagination

    public List<SellerDTO> getAllActiveSellers(int page, int size) {
        return sellerRepo.findAllActive(page, size).stream().map(entity -> {
            SellerDTO dto = new SellerDTO();
            BeanUtils.copyProperties(entity, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public long countActiveSellers() {
        return sellerRepo.countActive();
    }
}
