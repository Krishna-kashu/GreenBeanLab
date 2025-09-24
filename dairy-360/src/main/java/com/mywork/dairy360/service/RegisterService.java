package com.mywork.dairy360.service;

import com.mywork.dairy360.dto.RegisterDTO;
import com.mywork.dairy360.entity.RegisterEntity;

public interface RegisterService {
    boolean saveUser(RegisterDTO registerDTO);

    String checkEmail(String email);

    RegisterEntity getUserByEmail(String email);

    boolean login(String email, String rawPassword);

    void increaseFailedAttempts(RegisterEntity user);

    void resetFailedAttempts(RegisterEntity user);

    void lock(RegisterEntity user);

    boolean unlockWhenTimeExpired(RegisterEntity user);

    boolean update(RegisterEntity entity);

    void updatePassword(RegisterEntity user, String newPassword);

    void saveResetToken(RegisterEntity user, String token);

    RegisterEntity getByEmail(String email);

    RegisterEntity getByResetToken(String token);
}
