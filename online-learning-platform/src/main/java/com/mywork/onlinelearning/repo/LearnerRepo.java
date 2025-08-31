package com.mywork.onlinelearning.repo;

import com.mywork.onlinelearning.entity.LearnerEntity;

public interface LearnerRepo {

    boolean save(LearnerEntity entity);

    String checkMail(String email);

    Long checkPhone(Long phone);

    LearnerEntity getByMail(String email);

    void updateLoginCount(LearnerEntity learner);

    boolean updateEntity(LearnerEntity entity);

    LearnerEntity findByID(int id);
}
