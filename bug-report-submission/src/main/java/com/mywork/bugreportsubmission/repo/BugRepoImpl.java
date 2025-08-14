package com.mywork.bugreportsubmission.repo;

import com.mywork.bugreportsubmission.dto.BugDTO;
import com.mywork.bugreportsubmission.entity.BugEntity;
import org.hibernate.QueryException;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class BugRepoImpl implements BugRepo{

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-work");

    @Override
    public boolean save(BugEntity entity) {

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
    public List<BugEntity> findAll() {
        List<BugEntity> list = Collections.emptyList();
        EntityManager manager = null;
        try{
            manager = emf.createEntityManager();
            Query query = manager.createNamedQuery("findAll");

            list = query.getResultList();

        }catch (PersistenceException e){
            System.out.println("error in findAll "+e.getMessage());
        }finally {
           if( manager!=null) manager.close();
        }
        return list;
    }

    @Override
    public BugEntity findById(int id) {

        EntityManager manager = emf.createEntityManager();
        try {
            return manager.find(BugEntity.class, id);
        }catch (PersistenceException e){
            System.out.println("error in findById"+e.getMessage());
        }finally {
            if(manager!= null) manager.close();
        }
        return null;
    }

    @Override
    public boolean updateBugEntity(BugEntity entity) {
        System.out.println("running updateBugEntity in Repo, entity: "+entity);
        boolean isUpdated = false;
        EntityManager manager = null;
        try {
            manager = emf.createEntityManager();
            EntityTransaction transaction =  manager.getTransaction();
            transaction.begin();
            Integer rows = manager.createNamedQuery("updateBugEntity")
            .setParameter("reporterName", entity.getReporterName())
                    .setParameter("email", entity.getEmail())
                    .setParameter("title", entity.getTitle())
                    .setParameter("description", entity.getDescription())
                    .setParameter("stepsCount", entity.getStepsCount())
                    .setParameter("isCritical", entity.getIsCritical())
                    .setParameter("id", entity.getId()).executeUpdate();
            if(rows>0){
                isUpdated = true;
            }
            transaction.commit();
        }catch ( NoResultException | QueryException e){
            System.out.println("error in updateBugEntity method in repo: "+e.getMessage());
        }finally {
            if(manager!=null) manager.close();
        }
        return isUpdated;
    }

    @Override
    public boolean deleteById(int id) {
        System.out.println("deleteById method in repo");
        boolean isDeleted = false;
        EntityManager manager = null;
        try {
            manager = emf.createEntityManager();
            EntityTransaction transaction = manager.getTransaction();
            transaction.begin();

        }catch (QueryException | ){

        }finally {

        }
        return false;
    }

    public static void emfClose(){
        if(emf.isOpen()) emf.close();
    }
}