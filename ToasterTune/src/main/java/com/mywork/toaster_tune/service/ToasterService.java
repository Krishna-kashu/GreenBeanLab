package com.mywork.toaster_tune.service;

import com.mywork.toaster_tune.dto.ToasterDTO;

public interface ToasterService {
    boolean validate(ToasterDTO dto);
    double totalPrice(ToasterDTO dto);
}
