package com.mywork.onlinelearning.service;

import com.mywork.onlinelearning.dto.LearnerDTO;

public interface LearnerService {
    boolean valid(LearnerDTO dto);
    String checkEmail(String email);
    String checkPhone(Long phone);
    LearnerDTO getUserDTO(String email, String password);
}
