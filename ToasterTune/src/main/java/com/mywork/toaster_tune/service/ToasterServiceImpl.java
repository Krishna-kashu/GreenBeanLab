package com.mywork.toaster_tune.service;

import com.mywork.toaster_tune.dto.ToasterDTO;
import org.springframework.stereotype.Service;

@Service
public class ToasterServiceImpl implements ToasterService{

    public ToasterServiceImpl() {
        System.out.println("No-arg constructor of ToasterServiceImpl");
    }

    @Override
    public boolean validate(ToasterDTO dto) {
        if (dto == null) {
            return false;
        }
        boolean valid = true;
        if (dto.getModelName() == null || dto.getModelName().length() < 2) valid = false;
        if (dto.getPrice() == null || dto.getPrice() < 100) valid = false;
        if (dto.getMultipartFile() == null || dto.getMultipartFile().isEmpty()) valid = false;
        return valid;
    }

    @Override
    public double totalPrice(ToasterDTO dto) {
        return dto.getPrice() + (dto.getPrice() * 0.18);
    }
}
