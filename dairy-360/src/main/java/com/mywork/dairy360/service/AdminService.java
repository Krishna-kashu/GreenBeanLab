package com.mywork.dairy360.service;

import com.mywork.dairy360.dto.AdminDTO;
import com.mywork.dairy360.entity.AdminEntity;

import javax.transaction.Transactional;

public interface AdminService
{
    boolean save(AdminDTO adminDTO);

    AdminDTO checkAdminLoginPassword(String email,String password);

    AdminDTO getAdminDetailsByEmail(String email);

    boolean updateAdminProfileByEmail(String email,String adminName,String phoneNumber);

    void saveResetToken(String email, String token);

    boolean isValidResetToken(String token);

    boolean updatePassword(String token, String newPassword);

    AdminEntity getAdminEntityByEmail(String email);

    boolean updateProfileImage(String email, String imagePath);
}