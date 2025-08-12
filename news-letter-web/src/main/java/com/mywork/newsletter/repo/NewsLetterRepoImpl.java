package com.mywork.newsletter.repo;

import com.mywork.newsletter.dto.NewsLetterDTO;
import com.mywork.newsletter.entity.NewsLetterEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Repository
public class NewsLetterRepoImpl implements  NewsLetterRepo{

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-work");
    @Override
    public boolean save(NewsLetterEntity entity) {
        System.out.println("save method in repoImpl");

        if (entity!=null) {
            System.out.println("Repo: " + entity);
            EntityManager manager = null;
            EntityTransaction transaction = null;
            try {
                manager = emf.createEntityManager();
                transaction = manager.getTransaction();

                transaction.begin();
                manager.merge(entity);
                transaction.commit();
                System.out.println("saved entity: "+entity);

            }catch (PersistenceException e){
                if (transaction.isActive()) transaction.rollback();
                System.out.println("error in save in repo"+e.getMessage());
            }finally {
                if (manager!=null) manager.close();
            }
        }
        return true;
    }

    @Override
    public List<NewsLetterEntity> fetchAll() {
        List<NewsLetterEntity> letterEntities= Collections.emptyList();
        EntityManager manager = null;
        try{
            manager = emf.createEntityManager();
            Query query = manager.createNamedQuery("findAll");

            letterEntities  = query.getResultList();

        }catch (PersistenceException e){
            System.out.println("error in findAll "+e.getMessage());
        }finally {
            if( manager!=null) manager.close();
        }

        return letterEntities;
    }
}
