package com.mywork.dairy360.service;

import com.mywork.dairy360.dto.CollectMilkDTO;
import com.mywork.dairy360.entity.CollectMilkEntity;
import com.mywork.dairy360.repo.MilkRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MilkServiceImpl implements MilkService {

    @Autowired
    private MilkRepo repo;

    @Override
    public boolean validateAndSave(CollectMilkDTO dto) {
        if (dto == null) {
            System.out.println("MilkDTO is null, cannot save.");
            return false;
        }

        CollectMilkEntity entity = new CollectMilkEntity();
        BeanUtils.copyProperties(dto, entity);

        double total = dto.getPrice() * dto.getQuantity();
        entity.setTotalAmount(total);

        repo.save(entity);
        System.out.println("Milk details saved successfully.");
        return true;
    }

    @Override
    public boolean calculateAndUpdateTotalAmount(int id) {
        CollectMilkEntity entity = repo.findByPhoneNumber(String.valueOf(id));
        if (entity != null) {
            double total = entity.getPrice() * entity.getQuantity();
            return repo.updateTotalAmountById(total, id);
        }
        return false;
    }

    @Override
    public boolean isPhoneNumberExist(String phoneNumber) {
        return repo.findByPhoneNumber(phoneNumber) != null;
    }
}