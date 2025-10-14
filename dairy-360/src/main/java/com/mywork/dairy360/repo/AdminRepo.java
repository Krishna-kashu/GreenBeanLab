package com.mywork.dairy360.repo;

import com.mywork.dairy360.entity.AdminEntity;

import java.util.List;

public interface AdminRepo {
    boolean save(AdminEntity adminEntity);

    AdminEntity getPasswordByEmail(String email);

    boolean updateAdminProfileByEmail(String email,String adminName,String phoneNumber);

    AdminEntity getByResetToken(String token);

    AdminEntity getAdminEntityByEmail(String email);

    boolean updateProfileImage(String email, String imagePath);

    String checkMail(String email);

    List<AdminEntity> getAllAdmins();
}
