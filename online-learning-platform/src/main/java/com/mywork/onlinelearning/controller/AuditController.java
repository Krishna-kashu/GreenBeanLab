package com.mywork.onlinelearning.controller;


import com.mywork.onlinelearning.entity.LearnerAuditEntity;
import com.mywork.onlinelearning.repo.AuditRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class AuditController {

    @Autowired
    private AuditRepoImpl auditRepo;

    @GetMapping("allAudit")
    public String viewAllAudits(Model model) {
        System.out.println("viewAllAudits method in AuditController");
        List<LearnerAuditEntity> audits = auditRepo.findAllAudits();
        model.addAttribute("audits", audits);
        return "audit";
    }
}
