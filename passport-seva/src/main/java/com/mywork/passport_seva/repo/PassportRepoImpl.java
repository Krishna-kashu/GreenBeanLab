package com.mywork.passport_seva.repo;

import com.mywork.passport_seva.entity.PassportEntity;
import org.hibernate.QueryException;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Repository
public class PassportRepoImpl implements PassportRepo{

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-work");

    @Override
    public boolean save(PassportEntity entity) {

        System.out.println("save method in repoImpl");

        if (entity!=null) {
            System.out.println("Repo: " + entity);
            EntityManager manager = null;
            EntityTransaction transaction = null;
            try {
                manager = emf.createEntityManager();
                transaction = manager.getTransaction();

                transaction.begin();
                manager.persist(entity);
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
    public List<PassportEntity> findAll() {
        List<PassportEntity> passportEntities= Collections.emptyList();
        EntityManager manager = null;
        try{
            manager = emf.createEntityManager();
            Query query = manager.createNamedQuery("findAll");

            passportEntities  = query.getResultList();

        }catch (PersistenceException e){
            System.out.println("error in findAll "+e.getMessage());
        }finally {
            if( manager!=null) manager.close();
        }

        return passportEntities;
    }

    @Override
    public boolean checkMail(String mail) {

        System.out.println("check mail method in repo");
        EntityManager manager = null;
        try{
            manager = emf.createEntityManager();
            Query query = manager.createNamedQuery("checkMail");
            query.setParameter("email", mail);

            PassportEntity entity = (PassportEntity) query.getSingleResult();
            System.out.println("Email entity: "+entity);
            if(entity!=null){
                return true;
            }
        }catch (PersistenceException e){
            System.out.println("error in checkMail method in repo : "+e.getMessage());
        }finally {
            if(manager!=null) manager.close();
        }
        return false;
    }

    @Override
    public boolean checkPhone(long phoneNumber) {
        System.out.println("check phone number method in repo");
        EntityManager manager = null;

        try {
            manager = emf.createEntityManager();
            Query query = manager.createNamedQuery("checkNumber").
                    setParameter("phone", phoneNumber);
            PassportEntity  entity = (PassportEntity) query.getSingleResult();
            System.out.println(phoneNumber);

            if(entity!=null){
                return true;
            }
        }catch (PersistenceException e){
            System.out.println("error in checkPhone: "+e.getMessage());
        }finally {
            if(manager!=null) manager.close();
        }
        return false;
    }

    @Override
    public PassportEntity findById(int id) {
        EntityManager manager = emf.createEntityManager();
        try {
            return manager.find(PassportEntity.class, id);
        }catch (PersistenceException e){
            System.out.println("error in findById"+e.getMessage());
        }finally {
            if(manager!= null) manager.close();
        }
        return null;
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

            int row = manager.createNamedQuery("deleteById")
                    .setParameter("id", id).executeUpdate();
            if(row>0) isDeleted = true;
            transaction.commit();
        }catch (QueryException | NoResultException e){
            System.out.println("error in deleteById: "+e.getMessage());
        }finally {
            if (manager != null) manager.close();
        }
        return isDeleted;
    }

    @Override
    public boolean updateEntity(PassportEntity entity) {
        System.out.println("Running updateEntity in Repo, entity: " + entity);
        boolean isUpdated = false;
        EntityManager manager = null;

        try {
            manager = emf.createEntityManager();
            EntityTransaction transaction = manager.getTransaction();
            transaction.begin();

            Integer rows = manager.createNamedQuery("updateEntity")
                    .setParameter("passportOffice", entity.getPassportOffice())
                    .setParameter("givenName", entity.getGivenName())
                    .setParameter("surName", entity.getSurName())
                    .setParameter("dob", entity.getDob())
                    .setParameter("email", entity.getEmail())
                    .setParameter("phone", entity.getPhone())
                    .setParameter("sameLoginId", entity.isSameLoginId())
                    .setParameter("loginId", entity.getLoginId())
                    .setParameter("password", entity.getPassword())
                    .setParameter("hintQuestion", entity.getHintQuestion())
                    .setParameter("hintAnswer", entity.getHintAnswer())
                    .setParameter("id", entity.getId())
                    .executeUpdate();

            if (rows > 0) {
                isUpdated = true;
            }
            transaction.commit();

        } catch (NoResultException | QueryException e) {
            System.out.println("Error in updateEntity method in repo: " + e.getMessage());
        } finally {
            if (manager != null) manager.close();
        }
        return isUpdated;
    }


    public static void emfClose(){
        if(emf.isOpen()) emf.close();
    }
}
