package com.mywork.onlinelearning.repo;

import com.mywork.onlinelearning.entity.LearnerAuditEntity;

import java.util.List;

public interface AuditRepo {
    List<LearnerAuditEntity> findAllAudits();

    void save(LearnerAuditEntity audit);
}
