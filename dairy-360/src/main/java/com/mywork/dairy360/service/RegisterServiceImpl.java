package com.mywork.dairy360.service;

import com.mywork.dairy360.dto.RegisterDTO;
import com.mywork.dairy360.entity.RegisterEntity;
import com.mywork.dairy360.repo.RegisterRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegisterServiceImpl implements RegisterService{

    private static final int MAX_FAILED_ATTEMPTS = 3;
    private static final long LOCK_TIME_DURATION_MINUTES = 1;
    private static final Logger log = LoggerFactory.getLogger(RegisterServiceImpl.class);

    @Autowired
    private RegisterRepo registerRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public RegisterServiceImpl(){
        log.info("no-arg constructor of RegisterServiceImpl");
    }

    public boolean saveUser(RegisterDTO dto) {

        RegisterEntity existing = registerRepo.findByEmail(dto.getEmail());
        if (existing != null) {
            throw new IllegalStateException("Email already registered");
        }

        RegisterEntity entity = new RegisterEntity();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());

        String rawPassword = dto.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        entity.setPassword(encodedPassword);

        if (entity.getFailedAttempts() == null) {
            entity.setFailedAttempts(0);
        }
        if (entity.getAccountNonLocked() == null) {
            entity.setAccountNonLocked(true);
        }
        return registerRepo.save(entity);
    }

    @Override
    public String checkEmail(String email) {
        System.out.println("\ncheckMail method in service");
        RegisterEntity user = registerRepo.findByEmail(email);
        return (user != null) ? "exists" : "notExists";
    }

    @Override
    public RegisterEntity getUserByEmail(String email) {
        System.out.println("Fetching user by email: " + email);
        return registerRepo.getUserByEmail(email);
    }

    @Override
    public RegisterEntity getByEmail(String email) {
        System.out.println("getBYEmail method");
        return registerRepo.findByEmail(email);
    }

    @Override
    public RegisterEntity getByResetToken(String token) {
        return registerRepo.findByResetToken(token);
    }

    @Override
    public boolean login(String email, String rawPassword) {
        RegisterEntity user = getByEmail(email);

        if (user == null) {
            return false;
        }

        if (!user.getAccountNonLocked()) {
            if (unlockWhenTimeExpired(user)) {
                return passwordEncoder.matches(rawPassword, user.getPassword());
            }
            return false;
        }

        if (passwordEncoder.matches(rawPassword, user.getPassword())) {
            resetFailedAttempts(user);
            return true;
        } else {
            increaseFailedAttempts(user);
            return false;
        }
    }

    @Override
    public void increaseFailedAttempts(RegisterEntity user) {
        int newFailAttempts = user.getFailedAttempts() + 1;
        user.setFailedAttempts(newFailAttempts);

        if (newFailAttempts >= MAX_FAILED_ATTEMPTS) {
            lock(user);
        }

        registerRepo.save(user);
    }

    @Override
    public void resetFailedAttempts(RegisterEntity user) {
        user.setFailedAttempts(0);
        user.setAccountNonLocked(true);
        user.setLockTime(null);
        registerRepo.save(user);
    }

    @Override
    public void lock(RegisterEntity user) {
        user.setAccountNonLocked(false);
        user.setLockTime(LocalDateTime.now());
        registerRepo.save(user);
    }

    @Override
    public boolean unlockWhenTimeExpired(RegisterEntity user) {
        if (user.getLockTime() == null) {
            return false;
        }

        LocalDateTime unlockTime = user.getLockTime().plusMinutes(LOCK_TIME_DURATION_MINUTES);
        if (LocalDateTime.now().isAfter(unlockTime)) {
            resetFailedAttempts(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean update(RegisterEntity entity) {
        return registerRepo.update(entity);
    }

    @Override
    public void updatePassword(RegisterEntity user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        user.setResetTokenExpiry(null);
        registerRepo.save(user);
    }

    @Override
    public void saveResetToken(RegisterEntity user, String token) {
        user.setResetToken(token);
        user.setResetTokenExpiry(LocalDateTime.now().plusMinutes(10));
        registerRepo.save(user);
    }

}