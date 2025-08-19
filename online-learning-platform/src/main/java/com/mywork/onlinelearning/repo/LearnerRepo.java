package com.mywork.onlinelearning.repo;

import com.mywork.onlinelearning.entity.LearnerEntity;

public interface LearnerRepo {
    boolean save(LearnerEntity entity);
    String checkMail(String email);
    String checkPhone(Long phone);
    LearnerEntity getByMail(String email);
}
