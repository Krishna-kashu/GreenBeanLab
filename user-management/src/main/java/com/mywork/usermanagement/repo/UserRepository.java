package com.mywork.usermanagement.repo;

import com.mywork.usermanagement.entity.UserEntity;

import java.util.List;

public interface UserRepository {

    boolean save(UserEntity entity);

    List<UserEntity> fetchAll();

    UserEntity findById(int id);

    boolean updateEntity(UserEntity entity);

    boolean deleteBYId(int id);
}
