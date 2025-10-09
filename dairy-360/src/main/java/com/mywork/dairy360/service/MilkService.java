package com.mywork.dairy360.service;

import com.mywork.dairy360.dto.CollectMilkDTO;

public interface MilkService {
    boolean validateAndSave(CollectMilkDTO dto);

    boolean calculateAndUpdateTotalAmount(int id);

    boolean isPhoneNumberExist(String phoneNumber);
}
