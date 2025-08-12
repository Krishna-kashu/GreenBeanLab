package com.mywork.newsletter.service;

import com.mywork.newsletter.dto.NewsLetterDTO;
import com.mywork.newsletter.entity.NewsLetterEntity;
import com.mywork.newsletter.repo.NewsLetterRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsLetterServiceImpl implements NewsLetterService{
    @Autowired
    NewsLetterRepoImpl newsLetterRepo;

    @Override
    public boolean valid(NewsLetterDTO dto) {
        System.out.println("running valid method in NewsLetterService");

        if(dto==null){
            System.out.println("DTO is null");
            return false;
        }
        if (dto.getFirstName()==null || dto.getLastName().trim().isEmpty()){
            System.out.println("First name is invalid");
            return false;
        }
        if(dto.getEmail() == null || !dto.getEmail().contains("@")){
            System.out.println("Email is invalid");
            return  false;
        }
        NewsLetterEntity newsLetterEntity = new NewsLetterEntity();
        newsLetterEntity.setFirstName(dto.getFirstName());
        newsLetterEntity.setLastName(dto.getLastName());
        newsLetterEntity.setGender(dto.getGender());
        newsLetterEntity.setAge(dto.getAge());
        newsLetterEntity.setEmail(dto.getEmail());
        newsLetterEntity.setTopic(dto.getTopic());

        newsLetterRepo.save(newsLetterEntity);
        return true;
    }

    @Override
    public List<NewsLetterDTO> getAll() {

        System.out.println("invoking getAll in service");

        List<NewsLetterDTO> newsLetterDTOList = null;
        List<NewsLetterEntity> newsLetterEntities = newsLetterRepo.fetchAll();

        newsLetterDTOList = newsLetterEntities.stream().map(e-> {
            NewsLetterDTO dto = new NewsLetterDTO();
            dto.setId(e.getId());
            dto.setFirstName(e.getFirstName());
            dto.setLastName(e.getLastName());
            dto.setEmail(e.getEmail());
            dto.setGender(e.getGender());
            dto.setAge(e.getAge());
            dto.setTopic(e.getTopic());
            return dto;
        }).collect(Collectors.toList());

        newsLetterEntities.forEach(System.out::println);
        return newsLetterDTOList;
    }

    @Override
    public NewsLetterDTO getById(int id) {

        NewsLetterDTO dto = new NewsLetterDTO();
        NewsLetterEntity entity = newsLetterRepo.findById(id);

        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setAge(entity.getAge());
        dto.setGender(entity.getGender());
        dto.setEmail(entity.getEmail());
        dto.setTopic(entity.getTopic());

        return dto;
    }
}