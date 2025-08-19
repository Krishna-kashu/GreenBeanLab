package com.mywork.onlinelearning.service;

import com.mywork.onlinelearning.dto.LearnerDTO;
import com.mywork.onlinelearning.entity.LearnerEntity;
import com.mywork.onlinelearning.repo.LearnerRepoImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LearnerServiceImpl implements LearnerService{


    @Autowired
    private LearnerRepoImpl repo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public LearnerServiceImpl(){
        System.out.println("LearnerServiceImpl constructor");
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
        if (dto.getPassword()== null || dto.getPassword().trim().isEmpty()){
            System.out.println("\npassword is required");
            return false;
        }
        if (!dto.getPassword().equals(dto.getConfirmPassword())){
            System.out.println("\nPassword do not match");
            return false;
        }

        System.out.println("dto is valid");
        LearnerEntity entity = new LearnerEntity();
        BeanUtils.copyProperties(dto, entity);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setIsActive(true);

        return repo.save(entity);
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
        if (passwordEncoder.matches(password, entity.getPassword())){
            LearnerDTO dto = new LearnerDTO();
            BeanUtils.copyProperties(entity, dto);
            return dto;
        }
        System.out.println("Password mismatch for email: "+email);
        return null;
    }

}