package com.mywork.onlinelearning.service;

import com.mywork.onlinelearning.dto.LearnerDTO;
import com.mywork.onlinelearning.entity.LearnerEntity;
import com.mywork.onlinelearning.repo.LearnerRepoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Service
public class LearnerServiceImpl implements LearnerService{


    private static final Logger log = LoggerFactory.getLogger(LearnerServiceImpl.class);
    @Autowired
    private LearnerRepoImpl repo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmailSenderServiceImpl emailSenderService;


    private Map<String, Integer> failedAttempts = new HashMap<>();

    public LearnerServiceImpl(){
        System.out.println("LearnerServiceImpl constructor");
        log.info("LearnerServiceImpl from log");
    }

    @Override
    public boolean valid(LearnerDTO dto) {
        System.out.println("Running validate in LearnerServiceImpl");

        System.out.println("Service data: " + dto);

        if (dto == null){
            System.out.println("DTO is null");
            return false;
        }
        if(dto.getName()==null || dto.getName().trim().isEmpty()){
            System.out.println("Name is invalid");
            return false;
        }
        if (dto.getPhone() == null){
            System.out.println("\nphone number can not be null");
            return false;
        }
        if (dto.getDob() == null){
            System.out.println("\n dob can not be null");
            return false;
        }
        if (dto.getEmail() == null || !dto.getEmail().contains("@")){
            System.out.println("\nEmail is invalid");
            return false;
        }
        if (dto.getAddress() == null){
            System.out.println("\naddress can not be null");
            return false;
        }
        if (dto.getGender() == null){
            System.out.println("\ngender can not be null");
            return false;
        }
        if (dto.getState() == null){
            System.out.println("\nstate can not be null");
            return false;
        }

        System.out.println("dto is valid");
        LearnerEntity entity = new LearnerEntity();
        BeanUtils.copyProperties(dto, entity);

        String otp = String.valueOf((int)(Math.random() * 900000) + 100000);
        entity.setPassword(passwordEncoder.encode(otp));
        entity.setOtpGeneratedTime(LocalDateTime.now());
        entity.setIsActive(true);
        entity.setLoginCount(-1);
        entity.setLockTime(null);

        if (repo.save(entity)) {
            if (emailSenderService.sendOTP(dto.getEmail(), otp)) {
                System.out.println("Email sent successfully");
                return true;
            }

            System.out.println("Failed to send email");
            return false;
        }
        System.out.println("Entity not saved");
        return false;
    }

    @Override
    public String checkEmail(String email) {
        System.out.println("\ncheckMail method in service");
        return repo.checkMail(email);
    }

    @Override
    public Long checkPhone(Long phone) {
        System.out.println("\ncheckPhone method in service");
        return repo.checkPhone(phone);
    }

    @Override
    public boolean setPassword(String email, String password, String confirmPassword) {
        System.out.println("setPassword method in service");

        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match");
            return false;
        }

        LearnerEntity entity = repo.getByMail(email);
        if (entity == null) {
            return false;
        }

        entity.setPassword(passwordEncoder.encode(password));
        entity.setLoginCount(1);
        entity.setLockTime(null);
        repo.updateLoginCount(entity);
        return true;
    }

    public LearnerEntity getByEmail(String email) {
        return repo.getByMail(email);
    }

    @Override
    public boolean loginWithOtpOrPassword(String email, String inputPassword) {
        LearnerEntity learner = repo.getByMail(email);
        if (learner == null || !learner.getIsActive()) {
            return false;
        }

        if (learner.getLockTime() != null) {
            LocalDateTime now = LocalDateTime.now();
            if (now.isBefore(learner.getLockTime().plusMinutes(1))) {
                System.out.println("Account is temporarily locked");
                return false;
            } else {
                learner.setLockTime(null);
                learner.setLoginCount(0);
                repo.updateLoginCount(learner);
            }
        }

        boolean matches = passwordEncoder.matches(inputPassword, learner.getPassword());

        if (matches) {
            if (learner.getLoginCount() < 0) {
                return true;
            }
            learner.setLoginCount(0);
            repo.updateLoginCount(learner);
            return true;
        } else {
            learner.setLoginCount(learner.getLoginCount() + 1);
            if (learner.getLoginCount() >= 3) {
                learner.setLockTime(LocalDateTime.now());
                System.out.println("Account locked due to 3 failed attempts");
            }
            repo.updateLoginCount(learner);
            return false;
        }
    }

    @Override
    public LearnerDTO getByID(int id) {

        LearnerDTO dto = new LearnerDTO();
        LearnerEntity entity = repo.findByID(id);

        if (entity!=null){
            BeanUtils.copyProperties(entity, dto);
        }
        return dto;
    }

    @Override
    public String updateEntity(LearnerDTO dto) {

        System.out.println("updateEntity method in service "+"\tDTO in updateEntity method: "+dto);
        LearnerEntity entity = new LearnerEntity();
        BeanUtils.copyProperties( dto, entity);
        boolean updated = repo.updateEntity(entity);
        if (updated) return "Updated";
        return "Update failed";
    }

    @Override
    public LearnerDTO getByEmailDTO(String email) {
        LearnerEntity entity = repo.getByMail(email);
        if (entity == null) return null;

        LearnerDTO dto = new LearnerDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
    public void saveUpdatedLearner(LearnerEntity entity) {
        repo.updateLoginCount(entity);
    }
}