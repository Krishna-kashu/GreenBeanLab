package com.mywork.dairy360.service;

import com.mywork.dairy360.entity.AdminEntity;
import com.mywork.dairy360.entity.AuditEntity;
import com.mywork.dairy360.repo.AuditRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditServiceImpl implements AuditService{

    @Autowired
    private AuditRepoImpl auditRepository;

    public AuditServiceImpl(){
        System.out.println("no-arg constructor of AuditServiceImpl");
    }

    @Override
    public void logLogin(AdminEntity admin) {
        System.out.println("logLogin method in service");
        AuditEntity audit = new AuditEntity();
        audit.setAdmin(admin);
        audit.setAdminName(admin.getAdminName());
        audit.setLoginTime(LocalDateTime.now());
        audit.setLogoutTime(null);

        auditRepository.save(audit);
    }

    @Override
    public void logLogout(AdminEntity admin) {
        System.out.println("logLogout method in service");
        AuditEntity audit = auditRepository.findTopByAdminOrderByLoginTimeDesc(admin);
        if (audit != null && audit.getLogoutTime() == null) {
            audit.setLogoutTime(LocalDateTime.now());
            auditRepository.save(audit);
        }
    }

    @Override
    public List<AuditEntity> getAllAudits() {
        return auditRepository.findAll(); // fetch all rows
    }

}
