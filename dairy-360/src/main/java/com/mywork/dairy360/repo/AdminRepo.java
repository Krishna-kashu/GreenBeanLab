package com.mywork.dairy360.repo;

import com.mywork.dairy360.entity.AdminEntity;

public interface AdminRepo {
    boolean save(AdminEntity adminEntity);

    AdminEntity getPasswordByEmail(String email);

    boolean updateAdminProfileByEmail(String email,String adminName,String phoneNumber);


    AdminEntity getByResetToken(String token);

    AdminEntity getAdminEntityByEmail(String email);

    boolean updateProfileImage(String email, String imagePath);
}
