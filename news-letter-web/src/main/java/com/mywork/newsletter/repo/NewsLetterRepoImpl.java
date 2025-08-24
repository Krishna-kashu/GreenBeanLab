package com.mywork.newsletter.repo;

import com.mywork.newsletter.dto.NewsLetterDTO;
import com.mywork.newsletter.entity.NewsLetterEntity;
import org.hibernate.QueryException;
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

    @Override
    public NewsLetterEntity findByID(int id) {
        EntityManager manager = emf.createEntityManager();
        try {
            return manager.find(NewsLetterEntity.class, id);
        }catch (PersistenceException e){
            System.out.println("error in findById"+e.getMessage());
        }finally {
            if(manager!= null) manager.close();
        }
        return null;
    }

    @Override
    public boolean updateEntity(NewsLetterEntity entity) {

        System.out.println("update method in repo");
        System.out.println("Entity = "+entity);
        boolean isUpdated = false;
        EntityManager manager = null;

        try {
            manager = emf.createEntityManager();
            EntityTransaction transaction = manager.getTransaction();
            transaction.begin();
            Integer rows = manager.createNamedQuery("updateEntity")
                    .setParameter("firstName", entity.getFirstName())
                    .setParameter("lastName", entity.getLastName())
                    .setParameter("gender", entity.getGender())
                    .setParameter("age", entity.getAge())
                    .setParameter("email", entity.getEmail())
                    .setParameter("topic", entity.getTopic())
                    .setParameter("id", entity.getId())
                    .executeUpdate();
           if(rows>0 )isUpdated = true;
           transaction.commit();
        }catch (NoResultException | QueryException e){
            System.out.println("error in updateEntity method "+e.getMessage());
        }finally {
            if(manager!=null) manager.close();
        }
        return isUpdated;
    }

    @Override
    public boolean deleteById(int id) {
        System.out.println("delete by id method is running in repo");
        boolean isDeleted = false;
        EntityManager manager = null;
        try {
            manager = emf.createEntityManager();
            EntityTransaction transaction = manager.getTransaction();
            transaction.begin();

            int row = manager.createNamedQuery("deleteById")
                    .setParameter("id", id).executeUpdate();
            if(row>0){
                isDeleted = true;
                transaction.commit();
            }
        }catch (NoResultException | QueryException e){
            System.out.println("error in delete method "+e.getMessage());
        }finally {
            if(manager != null) manager.close();
        }
        return isDeleted;
    }

    @Override
    public boolean checkMail(String email) {
        System.out.println("check mail method in repo, email:  "+email);
        EntityManager manager = null;
        try {
            manager = emf.createEntityManager();
            Query query = manager.createNamedQuery("checkMail")
                    .setParameter("email", email);

            NewsLetterEntity entity = (NewsLetterEntity) query.getSingleResult();
            System.out.println("email already exists");
            if(entity!= null){
                return  true;
            }
        }catch ( PersistenceException e){
            System.out.println("error in checkmail"+e.getMessage());
        }finally {
            if(manager != null) manager.close();
        }
        return false;
    }

    public static void emfClose(){
        if(emf.isOpen()) emf.close();
    }
}
