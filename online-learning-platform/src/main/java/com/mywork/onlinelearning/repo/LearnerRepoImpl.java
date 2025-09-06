package com.mywork.onlinelearning.repo;

import com.mywork.onlinelearning.entity.LearnerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class LearnerRepoImpl implements LearnerRepo{

    private static final Logger log = LoggerFactory.getLogger(LearnerRepoImpl.class);

    @Autowired
    private EntityManagerFactory emf;

    @Override
    public boolean save(LearnerEntity entity) {

        System.out.println("save method in repo, entity: "+entity);

        log.info("Trying log");

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
                System.err.println("error in save method :" + e.getMessage());

                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                return false;
            }
            finally {
                if (manager != null && manager.isOpen()) {
                    manager.close();
                    System.out.println("EntityManager closed");
                }            }
        }
        return false;
    }

    @Override
    public String checkMail(String email) {
        System.out.println("check mail method in repository");
        EntityManager manager = null;

        try {
            manager =emf.createEntityManager();
            List<String> resultList = manager.createNamedQuery("checkMail", String.class)
                    .setParameter("email", email)
                    .getResultList();
//            if (resultList.isEmpty()) {
//                return null;
//            } else {
//                return resultList.get(0);
//            }
        return resultList.isEmpty() ? null : resultList.get(0);

        }catch (PersistenceException e){
            System.err.println("error in checkMail : "+e.getMessage());
            return null;
        }finally {
            if (manager!=null) manager.close();
            System.out.println("EntityManager closed");
        }
    }

    @Override
    public Long checkPhone(Long phone) {
        System.out.println("checkPhone method in repo");
        EntityManager manager = null;

        try {
            manager= emf.createEntityManager();
            List<Long> list= manager.createNamedQuery("checkPhone", Long.class)
                    .setParameter("phone", phone)
                    .getResultList();
            return list.isEmpty() ? null : list.get(0);

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
        try {
            manager = emf.createEntityManager();
            return manager.createNamedQuery("getByEmail", LearnerEntity.class)
                    .setParameter("email",email )
                    .getSingleResult();

        }catch (NoResultException e) {
            System.err.println("No user found for email: " + email);
            return null;
        }catch (PersistenceException e){
            System.err.println("error in checkMail : "+e.getMessage());
            return null;
        }finally {
            if (manager!=null) manager.close();
        }
    }

    @Override
    public void updateLoginCount(LearnerEntity learner) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(learner);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    @Override
    public boolean updateEntity(LearnerEntity entity){
        EntityManager manager = null;
        boolean isUpdated = false;

        try{
            manager = emf.createEntityManager();
            EntityTransaction  transaction = manager.getTransaction();
            transaction.begin();

            Integer rows = manager.createNamedQuery("updateDTO")
                    .setParameter("name", entity.getName())
                    .setParameter("gender", entity.getGender())
                    .setParameter("dob", entity.getDob())
                    .setParameter("phone", entity.getPhone())
                    .setParameter("state", entity.getState())
                    .setParameter("address", entity.getAddress())
                    .setParameter("password", entity.getPassword())
                    .setParameter("id", entity.getLearnerId())
                    .executeUpdate();
            manager.merge(entity);
            if (rows > 0) {
                isUpdated = true;
            }
            transaction.commit();

        }catch (PersistenceException e){
            System.out.println("error in updateDTO: "+e.getMessage());
        }finally {
            if (manager!=null) manager.close();
        }
        return isUpdated;
    }

    @Override
    public LearnerEntity findByID(int id) {
        EntityManager manage= emf.createEntityManager();

        try {
            return manage.find(LearnerEntity.class, id);
        }catch (PersistenceException e){
            System.err.println("error in findByID "+ e.getMessage());
        }finally {
            if (manage!=null) manage.close();
        }
        return null;
    }

//
//    @Override
//    public boolean updateOtpAndTime(LearnerEntity learner) {
//        EntityManager em = emf.createEntityManager();
//        LearnerEntity entity = em.find(LearnerEntity.class, learner.getLearnerId());
//        if (entity != null) {
//            entity.setPassword(learner.getPassword());
//            entity.setLastOtpSentTime(learner.getLastOtpSentTime());
//            em.getTransaction().begin();
//            em.merge(entity);
//            em.getTransaction().commit();
//            return true;
//        }
//        return false;
//    }
}