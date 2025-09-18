package com.mywork.dairy360.service;

import com.mywork.dairy360.dto.AdminDTO;
import com.mywork.dairy360.entity.AdminEntity;
import com.mywork.dairy360.repo.AdminRepoImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminRepoImpl adminRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private static final int LOCK_TIME_DURATION = 2;

    public AdminServiceImpl()
    {
        System.out.println("AdminServiceImpl constructor");
    }

    @Override
    public boolean save(AdminDTO adminDTO) {
        System.out.println("save method in service");
        System.out.println("service data: "+adminDTO);
        if(adminDTO.getPassword().equals(adminDTO.getConfirmPassword()))
        {
            System.out.println("password matched");
            AdminEntity adminEntity=new AdminEntity();
            BeanUtils.copyProperties(adminDTO,adminEntity);
            adminEntity.setPassword(passwordEncoder.encode(adminEntity.getPassword()));
            return adminRepository.save(adminEntity);
        }
        System.out.println("password not matched");
        return false;
    }

    @Override
    public AdminDTO checkAdminLoginPassword(String email, String password) {
        System.out.println("checkAdminLoginPassword method in service");

        AdminEntity adminEntity=adminRepository.getPasswordByEmail(email);
        if(adminEntity==null)
            return null;

        if (!adminEntity.getAccountNonLocked()) {
            System.out.println("Account locked for email: " + email);
            if (adminEntity.getLockTime() != null &&
                    adminEntity.getLockTime().plusMinutes(LOCK_TIME_DURATION).isBefore(LocalDateTime.now())) {

                adminEntity.setAccountNonLocked(true);
                adminEntity.setFailedAttempts(0);
                adminEntity.setLockTime(null);
                adminRepository.save(adminEntity);
            } else {
                return null;
            }
        }

        if(passwordEncoder.matches(password,adminEntity.getPassword()))
        {
            adminEntity.setFailedAttempts(0);
            adminRepository.save(adminEntity);

            AdminDTO adminDTO=new AdminDTO();
            BeanUtils.copyProperties(adminEntity,adminDTO);
            System.out.println("Password match");
            return adminDTO;
        }else {
            int attempts = adminEntity.getFailedAttempts() + 1;
            adminEntity.setFailedAttempts(attempts);
            if (attempts >= 3) {
                adminEntity.setAccountNonLocked(false);
                adminEntity.setLockTime(LocalDateTime.now());
                System.out.println("Account locked for email: " + email);
            }
            adminRepository.save(adminEntity);
            return null;
        }
    }

    @Override
    public AdminDTO getAdminDetailsByEmail(String email) {
        System.out.println("getAdminDetailsByEmail method in service");
        AdminEntity adminEntity=adminRepository.getPasswordByEmail(email);
        if(adminEntity!=null)
        {
            AdminDTO adminDTO=new AdminDTO();
            BeanUtils.copyProperties(adminEntity,adminDTO);
            return adminDTO;
        }
        return null;
    }

    @Override
    public boolean updateAdminProfileByEmail(String email, String adminName, String phoneNumber) {

        System.out.println("updateAdminProfileByEmail method in service");
        if (adminName == null || !adminName.matches("^[A-Za-z ]{2,50}$")) {
            System.err.println("Invalid Name: " +adminName);
            return false;
        }
        if (phoneNumber == null || !phoneNumber.matches("^[0-9]{10}$")) {
            System.err.println("Invalid Phone Number: " +phoneNumber);
            return false;
        }
        return adminRepository.updateAdminProfileByEmail(email,adminName,phoneNumber);
    }

    @Override
    public void saveResetToken(String email, String token) {
        System.out.println("save reset token method in service");
        AdminEntity admin = adminRepository.getPasswordByEmail(email);
        if (admin != null) {
            admin.setResetToken(token);
            admin.setTokenExpiry(LocalDateTime.now().plusMinutes(15));
            adminRepository.save(admin);
        }
    }

    @Override
    public boolean isValidResetToken(String token) {
        AdminEntity admin = adminRepository.getByResetToken(token);
        return admin != null && admin.getTokenExpiry().isAfter(LocalDateTime.now());
    }

    @Override
    public boolean updatePassword(String token, String newPassword) {
        System.out.println("updatePassword method in serviceImpl");

        AdminEntity admin = adminRepository.getByResetToken(token);

        if (admin == null || admin.getTokenExpiry().isBefore(LocalDateTime.now())) return false;

        admin.setPassword(passwordEncoder.encode(newPassword));
        admin.setResetToken(null);
        admin.setTokenExpiry(null);

        admin.setFailedAttempts(0);
        admin.setAccountNonLocked(true);
        admin.setLockTime(null);

        adminRepository.save(admin);
        return true;
    }

    @Override
    public AdminEntity getAdminEntityByEmail(String email) {
        return adminRepository.getAdminEntityByEmail(email);
    }

    @Override
    public boolean updateProfileImage(String email, String imagePath) {
        return adminRepository.updateProfileImage(email, imagePath);
    }

}
