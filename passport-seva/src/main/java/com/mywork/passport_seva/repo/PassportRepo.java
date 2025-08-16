package com.mywork.passport_seva.repo;

import com.mywork.passport_seva.entity.PassportEntity;

import java.util.List;

public interface PassportRepo {
    boolean save(PassportEntity entity);
    List<PassportEntity> findAll();
    boolean checkMail(String mail);
    boolean checkPhone(long phoneNumber);
}
