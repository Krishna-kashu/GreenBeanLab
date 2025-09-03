package com.mywork.usermanagement.service;

import com.mywork.usermanagement.dto.UserDTO;
import com.mywork.usermanagement.entity.UserEntity;
import com.mywork.usermanagement.repo.UserRepositoryImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositoryImpl repository;

    @Override
    public boolean save(UserDTO dto) {
        System.out.println("save method in UserServiceImpl");
        if (dto==null){
            System.out.println("DTO null");
            return false;
        }

        System.out.println("DTO is not null");
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(dto, entity);
        repository.save(entity);
        return true;
    }

    @Override
    public List<UserDTO> getAll() {
        System.out.println("getAll method in service");
        List<UserDTO> userDTOS = null;
        List<UserEntity> userEntities = repository.fetchAll();

        userDTOS = userEntities.stream().map(e->{UserDTO userDTO = new UserDTO();
        userDTO.setUserId(e.getUserId());
        userDTO.setUserName(e.getUserName());
        userDTO.setAge(e.getAge());
        userDTO.setGender(e.getGender());
        userDTO.setEmail(e.getEmail());
        userDTO.setPhoneNumber(e.getPhoneNumber());

        return userDTO;
        }).collect(Collectors.toList());

        return userDTOS;
    }

    @Override
    public UserDTO getById(int id) {
        UserDTO dto = new UserDTO();
        UserEntity entity = repository.findById(id);

        if (entity!=null){
            BeanUtils.copyProperties(entity,dto);
        }return dto;
    }

    @Override
    public String updateDto(UserDTO dto) {
        System.out.println("updateDto method in service "+"\t dto in updateDto: "+dto);
        UserEntity entity = new UserEntity();

        BeanUtils.copyProperties(dto, entity);
        boolean updated= repository.updateEntity(entity);
        if (updated){
            return "updated";
        }return "update failed";

    }

    @Override
    public String deleteDto(int id) {
        System.out.println("deleteDto method in service");
        boolean deleted = repository.deleteBYId(id);
        if (deleted){
            return "deleted";
        }
        return "delete failed";
    }
}
