package com.mywork.bugreportsubmission.service;

import com.mywork.bugreportsubmission.dto.BugDTO;
import com.mywork.bugreportsubmission.entity.BugEntity;

import java.util.List;

public interface BugService {
    boolean validate(BugDTO dto);
    List<BugDTO> getAll();
    BugDTO getById(int id);
    String updateBugDTO(BugDTO dto);
    String deleteById(int id);
    List<BugDTO> searchByReporterName(String reporterName);
}