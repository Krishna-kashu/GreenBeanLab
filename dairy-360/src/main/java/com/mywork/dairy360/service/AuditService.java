package com.mywork.dairy360.service;


import com.mywork.dairy360.entity.AdminEntity;
import com.mywork.dairy360.entity.AuditEntity;

import java.util.List;

public interface AuditService {


    void logLogin(AdminEntity admin);

    void logLogout(AdminEntity admin);

    List<AuditEntity> getAllAudits();
}
