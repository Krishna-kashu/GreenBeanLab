package com.mywork.contact_app.service;

import com.mywork.contact_app.dto.ContactDTO;

public interface ContactService {
    boolean valid(ContactDTO contactDTO);
}
