package com.mywork.onlinelearning.service;

import com.mywork.onlinelearning.dto.LearnerDTO;

public interface LearnerService {
    boolean valid(LearnerDTO dto);

    String checkEmail(String email);
    String checkPhone(Long phone);
    LearnerDTO getUserDTO(String email, String password);

    String generateOTP(String email);

    boolean verifyOTP(String email, String otp);

    boolean resetPassword(String email, String newPassword, String confirmPassword);

}
