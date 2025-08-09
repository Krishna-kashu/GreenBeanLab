package com.mywork.contact_app.repo;

import com.mywork.contact_app.dto.ContactDTO;

public interface ContactRepo {
    boolean save(ContactDTO contactDTO);
}
