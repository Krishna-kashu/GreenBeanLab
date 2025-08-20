package com.mywork.onlinelearning.repo;

import com.mywork.onlinelearning.entity.LearnerEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class LearnerRepoImpl implements LearnerRepo{

    private final static EntityManagerFactory emf = Persistence
            .createEntityManagerFactory("my-work");

    @Override
    public boolean save(LearnerEntity entity) {

        System.out.println("save method in repo, entity: "+entity);

        if(entity!=null) {
            EntityManager manager = null;
            EntityTransaction transaction = null;

            try{
                manager = emf.createEntityManager();
                transaction= manager.getTransaction();
                transaction.begin();
                if (entity.getLearnerId() == null) {
                    manager.persist(entity);
                } else {
                    manager.merge(entity);
                }
                transaction.commit();
                return true;

            }catch (PersistenceException e){
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                System.err.println("error in save method :" + e.getMessage());
                return false;
            }
            finally {
                if (manager!=null) manager.close();
            }
        }
        return false;
    }

    @Override
    public String checkMail(String email) {
        System.out.println("check mail method in repository");
        EntityManager manager = null;

        try {
            manager =emf.createEntityManager();
            List<String> resultList = manager.createNamedQuery("checkMail")
                    .setParameter("email", email)
                    .getResultList();
            if (resultList.isEmpty()) {
                return null;
            } else {
                return resultList.get(0);
            }
        }catch (PersistenceException e){
            System.err.println("error in checkMail : "+e.getMessage());
            return null;
        }finally {
            if (manager!=null) manager.close();
        }
    }

    @Override
    public String checkPhone(Long phone) {
        System.out.println("checkPhone method in repo");
        EntityManager manager = null;

        try {
            manager= emf.createEntityManager();
            Long number = (Long) manager.createNamedQuery("checkPhone")
                    .setParameter("phone", phone)
                    .getSingleResult();
            return number != null ? String.valueOf(number) : null;

        }catch (NoResultException e){
            System.err.println("error in checkMail : "+e.getMessage());
            System.out.println("phone number not found: "+phone);
            return null;
        }finally {
            if (manager!=null) manager.close();
        }
    }

    @Override
    public LearnerEntity getByMail(String email) {
        System.out.println("getByMail method in repo");
        EntityManager manager = null;
        LearnerEntity entity = null;
        try {
            manager = emf.createEntityManager();
            entity = (LearnerEntity) manager.createNamedQuery("getByEmail").setParameter("email",email )
                    .getSingleResult();

        }catch (NoResultException e) {
            System.err.println("No user found for email: " + email);
            return null;
        }catch (PersistenceException e){
            System.err.println("error in checkMail : "+e.getMessage());
        }finally {
            if (manager!=null) manager.close();
        }
        return entity;
    }
}
