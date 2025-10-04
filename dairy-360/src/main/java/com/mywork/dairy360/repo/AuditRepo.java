package com.mywork.dairy360.repo;

import com.mywork.dairy360.entity.AdminEntity;
import com.mywork.dairy360.entity.AdminAuditEntity;

import java.util.List;

public interface AuditRepo {

    boolean save(AdminAuditEntity auditEntity);

    List<AdminAuditEntity> findAll();

    AdminAuditEntity findTopByAdminOrderByLoginTimeDesc(AdminEntity admin);
}
