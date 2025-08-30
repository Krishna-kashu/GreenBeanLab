package com.mywork.passport_seva.service;

import com.mywork.passport_seva.dto.PassportDTO;
import com.mywork.passport_seva.entity.PassportEntity;

import java.util.List;

public interface PassportService {
    boolean save(PassportDTO dto);
    List<PassportDTO> getAll();
    boolean checkEmail(String email);
    boolean checkPhoneNumber(long phone);
    String deleteById(int id);
    String updateEntity(PassportDTO dto);
    PassportDTO getById(int id);
}