package com.mywork.dairy360.repo;

import com.mywork.dairy360.entity.AdminEntity;
import com.mywork.dairy360.entity.AuditEntity;

import java.util.List;

public interface AuditRepo {

    boolean save(AuditEntity auditEntity);

    List<AuditEntity> findAll();

    AuditEntity findTopByAdminOrderByLoginTimeDesc(AdminEntity admin);
}
