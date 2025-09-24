package com.mywork.dairy360.repo;

import com.mywork.dairy360.entity.RegisterEntity;

public interface RegisterRepo {
    boolean save(RegisterEntity registerEntity);

    String checkMail(String email);

    RegisterEntity getUserByEmail(String email);

    RegisterEntity findByEmail(String email);

    RegisterEntity findByResetToken(String token);

    boolean update(RegisterEntity entity);
}