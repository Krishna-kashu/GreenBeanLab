package com.mywork.newsletter.service;

import com.mywork.newsletter.dto.NewsLetterDTO;

import java.util.List;

public interface NewsLetterService {
    boolean valid(NewsLetterDTO dto);
    List<NewsLetterDTO > getAll();
    NewsLetterDTO getById(int id);
    String updateDto(NewsLetterDTO dto);
    String deleteDto(int id);
    boolean checkMail(String email);
}
