package com.mywork.bugreportsubmission.repo;

import com.mywork.bugreportsubmission.entity.BugEntity;

import java.util.List;

public interface BugRepo {
    boolean save(BugEntity entity);
    List<BugEntity> findAll();
}
