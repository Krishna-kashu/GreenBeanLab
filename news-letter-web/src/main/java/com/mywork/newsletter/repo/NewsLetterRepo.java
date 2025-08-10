package com.mywork.newsletter.repo;

import com.mywork.newsletter.dto.NewsLetterDTO;
import com.mywork.newsletter.entity.NewsLetterEntity;

public interface NewsLetterRepo {
    boolean save(NewsLetterEntity entity);
}
