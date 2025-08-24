package com.mywork.passport_seva.service;

import com.mywork.passport_seva.dto.PassportDTO;
import com.mywork.passport_seva.entity.PassportEntity;
import com.mywork.passport_seva.repo.PassportRepoImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassportServiceImpl implements PassportService{

    @Autowired
    PassportRepoImpl repo;

    @Override
    public boolean save(PassportDTO dto) {

        if(dto==null){
            System.out.println("DTO is null");
            return false;
        }
        if (dto.getGivenName()==null || dto.getGivenName().trim().isEmpty()){
            System.out.println("Name is invalid");
            return false;
        }
        if(dto.getEmail() == null || !dto.getEmail().contains("@")){
            System.out.println("Email is invalid");
            return  false;
        }

        if(dto.getPassword() == null || dto.getPassword().trim().isEmpty()) {
            System.out.println("Password is required");
            return false;
        }

        if(!dto.getPassword().equals(dto.getConfirmPassword())) {
            System.out.println("Passwords do not match");
            return false;
        }

        PassportEntity entity = new PassportEntity();
        BeanUtils.copyProperties(dto, entity);

        return repo.save(entity);
    }

    @Override
    public List<PassportDTO> getAll() {
        System.out.println("invoking getAll in service");

        List<PassportDTO> passportDTOS = null;
        List<PassportEntity> passportEntities = repo.findAll();

        passportDTOS = passportEntities.stream().map(e-> {
            PassportDTO dto = new PassportDTO();
            BeanUtils.copyProperties(e, dto);
            return dto;
        }).collect(Collectors.toList());

        passportEntities.forEach(System.out::println);
        return passportDTOS;
    }

    @Override
    public boolean checkEmail(String email) {
        System.out.println("checkEmail method in service");
        return repo.checkMail(email);
    }

    @Override
    public boolean checkPhoneNumber(long phone) {
        System.out.println("checkPhoneNumber method in service");
        return repo.checkPhone(phone);
    }

    @Override
    public PassportDTO getById(int id) {
        PassportDTO dto = new PassportDTO();
        PassportEntity entity = repo.findById(id);

        if(entity != null){
            BeanUtils.copyProperties(entity, dto);
        }
        return dto;
    }

    @Override
    public String updateEntity(PassportDTO dto) {
        System.out.println("updateEntity method in service");
        System.out.println("dto in updateEntity: "+dto);
        PassportEntity entity = new PassportEntity();
        BeanUtils.copyProperties(dto, entity);
        boolean updated = repo.updateEntity(entity);
        if(updated){
            return  "Updated";
        }
        return "update Failed";
    }

    @Override
    public String deleteById(int id) {
        System.out.println("deleteById service impl method");
        boolean deleted = repo.deleteById(id);
        if(deleted){
            return "deleted";
        }
        return "not deleted";
    }
}