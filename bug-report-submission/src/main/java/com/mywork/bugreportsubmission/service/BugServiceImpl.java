package com.mywork.bugreportsubmission.service;

import com.mywork.bugreportsubmission.dto.BugDTO;
import com.mywork.bugreportsubmission.entity.BugEntity;
import com.mywork.bugreportsubmission.repo.BugRepoImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BugServiceImpl implements BugService{
    @Autowired
    BugRepoImpl repo;
    @Override
    public boolean validate(BugDTO dto) {
        System.out.println("running validate method in BugServiceImpl");

        if(dto==null){
            System.out.println("DTO is null");
            return false;
        }
        if (dto.getTitle()==null || dto.getTitle().trim().isEmpty()){
            System.out.println("Title is invalid");
            return false;
        }
        if(dto.getEmail() == null || !dto.getEmail().contains("@")){
            System.out.println("Email is invalid");
            return  false;
        }

        BugEntity bugEntity = new BugEntity();
        bugEntity.setReporterName(dto.getReporterName());
        bugEntity.setTitle(dto.getTitle());
        bugEntity.setEmail(dto.getEmail());
        bugEntity.setStepsCount(dto.getStepsCount());
        bugEntity.setDescription(dto.getDescription());
        bugEntity.setIsCritical(dto.getIsCritical());

        repo.save(bugEntity);
        return true;
    }

    @Override
    public List<BugDTO> getAll() {

        System.out.println("get All method is running in serviceImpl");

        List<BugDTO > bugDTOList = null;
        List<BugEntity> bugEntityList = repo.findAll();

        bugDTOList = bugEntityList.stream().map(e->{BugDTO dto = new BugDTO();
            dto.setReporterName(e.getReporterName());
            dto.setId(e.getId());
            dto.setEmail(e.getEmail());
            dto.setTitle(e.getTitle());
            dto.setDescription(e.getDescription());
            dto.setStepsCount(e.getStepsCount());
            dto.setIsCritical(e.getIsCritical());
            return dto;

        }).collect(Collectors.toList());

        bugEntityList.forEach(e-> System.out.println(e));

        return bugDTOList;
    }

    @Override
    public BugDTO getById(int id) {

        BugDTO dto = new BugDTO();
        BugEntity entity = repo.findById(id);
        dto.setId(entity.getId());
        dto.setReporterName(entity.getReporterName());
        dto.setEmail(entity.getEmail());
        dto.setTitle(entity.getTitle());
        dto.setStepsCount(entity.getStepsCount());
        dto.setDescription(entity.getDescription());
        dto.setIsCritical(entity.getIsCritical());

        return dto;
    }

    @Override
    public String updateBugDTO(BugDTO dto) {
        System.out.println("updateBugDTO method in service ");
        System.out.println("dto in updateBug: "+dto);
        BugEntity entity = new BugEntity();

        BeanUtils.copyProperties(dto,entity);
        boolean isUpdated = repo.updateBugEntity(entity);

        if(isUpdated){
            return "updated";
        }
        return "update failed";
    }

    @Override
    public String deleteById(int id) {
        System.out.println("deleteById method in service");
        boolean isDeleted = repo.deleteById(id);
        if(isDeleted) {
            return "deleted";
        }
        return "delete failed";
    }

    @Override
    public List<BugDTO> searchByReporterName(String reporterName) {
        List<BugEntity> entities = repo.findByReporterName(reporterName);
        List<BugDTO> dtos = new ArrayList<>();

        for (BugEntity entity: entities){
            BugDTO dto = new BugDTO();
            BeanUtils.copyProperties(entity, dto);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public boolean checkEmail(String email) {
        System.out.println("checkEmail method in service");
        return repo.checkMail(email);
    }
}