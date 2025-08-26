package com.mywork.onlinelearning.service;

import com.mywork.onlinelearning.dto.LearnerDTO;

public interface LearnerService {
    boolean valid(LearnerDTO dto);

    String checkEmail(String email);

    Long checkPhone(Long phone);

    boolean setPassword(String email, String password, String confirmPassword);

    boolean loginWithOtpOrPassword(String email, String inputPassword);

    String updateEntity(LearnerDTO dto);

    LearnerDTO getByID(int id);

    LearnerDTO getByEmailDTO(String email);
}