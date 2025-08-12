package com.mywork.bugreportsubmission.service;

import com.mywork.bugreportsubmission.dto.BugDTO;
import com.mywork.bugreportsubmission.entity.BugEntity;
import com.mywork.bugreportsubmission.repo.BugRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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
}