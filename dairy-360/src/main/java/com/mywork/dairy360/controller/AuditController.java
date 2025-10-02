package com.mywork.dairy360.controller;


import com.mywork.dairy360.dto.AdminAuditDTO;
import com.mywork.dairy360.entity.AuditEntity;
import com.mywork.dairy360.service.AdminServiceImpl;
import com.mywork.dairy360.service.AuditServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class AuditController {

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private AuditServiceImpl auditService;

    @GetMapping("/audit-info")
    public String auditInfoPage(Model model) {
        System.out.println("auditInfoPage method in AuditController");
        List<AuditEntity> auditEntities = auditService.getAllAudits();

        List<AdminAuditDTO> auditList = auditEntities.stream().map(audit -> {
            AdminAuditDTO dto = new AdminAuditDTO();
            dto.setId(audit.getId());
            if (audit.getAdmin() != null) {
                dto.setAdmin(audit.getAdmin());
            }
            dto.setAdminName(audit.getAdminName());
            dto.setLoginTime(audit.getLoginTime());
            dto.setLogoutTime(audit.getLogoutTime());
            return dto;
        }).collect(Collectors.toList());

        model.addAttribute("auditList", auditList);
        return "auditInfo";
    }
}
