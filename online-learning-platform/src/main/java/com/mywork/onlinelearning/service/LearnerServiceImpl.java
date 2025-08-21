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

    public LearnerServiceImpl(){
        System.out.println("LearnerServiceImpl constructor");
        log.info("LearnerServiceImpl from log");
    }

    @Override
    public boolean valid(LearnerDTO dto) {
        System.out.println("Running validate in FanServiceImpl");

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
//        entity.setPassword(passwordEncoder.encode(otp));
//        entity.setOtp(null);
//        entity.setOtpExpiry(LocalDateTime.now().plusMinutes(2));
//        entity.setResetFlag(null);
//        entity.setIsActive(true);
//        entity.setPassword(null);
//        entity.setPassword(passwordEncoder.encode(dto.getPassword()));

        entity.setPassword(passwordEncoder.encode(otp));
        entity.setOtp(null);
        entity.setOtpExpiry(null);
        entity.setResetFlag(null);
        entity.setFirstLoginDone(false);

        boolean saved = repo.save(entity);
        if (saved){
            emailSenderService.sendOTP(dto.getEmail(), otp);
        }
        return saved;
    }

    @Override
    public String checkEmail(String email) {
        System.out.println("\ncheckMail method in service");
        return repo.checkMail(email);
    }

    @Override
    public String checkPhone(Long phone) {
        System.out.println("\ncheckPhone method in service");
        return repo.checkPhone(phone);
    }

    @Override
    public LearnerDTO getUserDTO(String email, String password) {
        System.out.println("getUserDTO method in service");
        LearnerEntity entity = repo.getByMail(email);

        if (entity==null){
            System.out.println("\nno user found with email:"+email);
            return null;
        }
        System.out.println("ResetFlag before resetting password: " + entity.getResetFlag());

//        if (passwordEncoder.matches(password, entity.getPassword())){
//            LearnerDTO dto = new LearnerDTO();
//            BeanUtils.copyProperties(entity, dto);
//            return dto;
//        }

        if (entity.getFirstLoginDone() == Boolean.FALSE) {
            if (passwordEncoder.matches(password, entity.getPassword())) {
                LearnerDTO dto = new LearnerDTO();
                BeanUtils.copyProperties(entity, dto);
                return dto;
            }
        } else {
            if (passwordEncoder.matches(password, entity.getPassword())) {
                LearnerDTO dto = new LearnerDTO();
                BeanUtils.copyProperties(entity, dto);
                return dto;
            }
        }

        System.out.println("Password mismatch for email: "+email);
        return null;
    }

    @Override
    public String generateOTP(String email) {
        LearnerEntity entity = repo.getByMail(email);
        if (entity == null) return null;

        String otp = String.valueOf((int)(Math.random() * 900000)+100000);
        System.out.println("otp..........:"+otp);
        entity.setOtp(passwordEncoder.encode(otp));

        entity.setOtpExpiry(LocalDateTime.now().plusMinutes(2));
        entity.setResetFlag(2);

        repo.save(entity);
        emailSenderService.sendOTP(email,otp);

        return otp;
    }

    @Override
    public boolean verifyOTP(String email, String otp) {
        LearnerEntity entity = repo.getByMail(email);
        if (entity == null ) return false;

        if (entity.getOtpExpiry().isBefore(LocalDateTime.now())) return false;

        if (passwordEncoder.matches(otp, entity.getOtp())) {
            entity.setOtp(null);
            entity.setOtpExpiry(null);
            entity.setResetFlag(1);
            repo.save(entity);
            return true;
        }
        return false;
    }
        @Override
        public boolean resetPassword (String email, String newPassword, String confirmPassword){
            if (!newPassword.equals(confirmPassword)) {
                System.out.println("password do not match");
                return false;
            }

            LearnerEntity entity = repo.getByMail(email);

            if (entity == null){
                System.out.println("No user found with email: " + email);
                return false;
            }
            if(entity.getResetFlag() == null || entity.getResetFlag() != 1) {
                System.out.println("Reset not allowed, current resetFlag = " + entity.getResetFlag());
                return false;
            }

            entity.setPassword(passwordEncoder.encode(newPassword));
            entity.setFirstLoginDone(true);
            entity.setResetFlag(null);
            repo.save(entity);

            System.out.println("Password reset successful for " + email);
            return true;
        }
}