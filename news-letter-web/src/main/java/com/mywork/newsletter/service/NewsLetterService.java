package com.mywork.newsletter.service;

import com.mywork.newsletter.dto.NewsLetterDTO;

import java.util.List;

public interface NewsLetterService {
    boolean valid(NewsLetterDTO dto);
    List<NewsLetterDTO > getAll();
}
