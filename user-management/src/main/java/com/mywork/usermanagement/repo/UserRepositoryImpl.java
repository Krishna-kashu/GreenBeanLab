package com.mywork.usermanagement.repo;

import com.mywork.usermanagement.entity.AuditInfoEntity;
import com.mywork.usermanagement.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository{

    @Autowired
    EntityManagerFactory emf;

    @Override
    public boolean save(UserEntity entity) {

        if (entity!=null){
            System.out.println("entity in repo: "+entity);
            EntityManager em =null;
            EntityTransaction transaction = null;

            try {
                em = emf.createEntityManager();
                transaction = em.getTransaction();

                transaction.begin();
                if (entity.getUserId() == null) {
                    em.persist(entity);
                } else {
                    em.merge(entity);
                }
                transaction.commit();
                System.out.println("entity saved: "+entity);
            }catch (PersistenceException e){
                if (transaction.isActive()) transaction.rollback();
                System.err.println("error in save method in repo "+e.getMessage());

            }finally {
                if (em!=null) em.close();
            }
        }
        return true;
    }

    @Override
    public List<UserEntity> fetchAll() {
        List<UserEntity> list = Collections.emptyList();
        EntityManager manager = null;

        try {
            manager = emf.createEntityManager();
            Query query = manager.createNamedQuery("allUser");
            list = query.getResultList();
        }catch (PersistenceException e){
            System.err.println("error in fetchAll "+e.getMessage());
        }finally {
            if (manager != null) manager.close();
        }
        return list;
    }

    @Override
    public UserEntity findById(int id) {
        EntityManager manager = emf.createEntityManager();
        try {
            return manager.find(UserEntity.class, id);
        }catch (PersistenceException e){
            System.err.println("error in findById "+e.getMessage());
        }finally {
            if (manager!=null) manager.close();
        }
        return null;
    }

    @Override
    public boolean updateEntity(UserEntity entity) {

        System.out.println("updateEntity method in repoImpl");
        boolean isUpdated = false;
        EntityManager manager= null;
        try {
            manager = emf.createEntityManager();
            EntityTransaction transaction = manager.getTransaction();
            transaction.begin();

            Integer row = manager.createNamedQuery("updateEntity")
                    .setParameter( "userName", entity.getUserName())
                    .setParameter("gender", entity.getGender())
                    .setParameter("age", entity.getAge())
                    .setParameter("email", entity.getEmail())
                    .setParameter("phoneNumber", entity.getPhoneNumber())
                    .setParameter("id", entity.getUserId()).executeUpdate();

            if (row>0) isUpdated=true;
            transaction.commit();
        }catch (PersistenceException e){
            System.err.println("error in update method "+e.getMessage());
        }finally {
            if (manager!=null) manager.close();
        }
        return isUpdated;
    }

//    @Override
//    public boolean deleteBYId(int id) {
//        System.out.println("deleteById method is running in repo ");
//
//        boolean isDeleted = false;
//        EntityManager manager = null;
//        try {
//            manager = emf.createEntityManager();
//            EntityTransaction transaction = manager.getTransaction();
//            transaction.begin();
//
//            int row = manager.createNamedQuery("deleteById")
//                    .setParameter("id", id).executeUpdate();
//
//            if (row>0){
//                isDeleted = true;
//                transaction.commit();
//            }
//        }catch (PersistenceException e){
//            System.err.println("error in delete by id "+e.getMessage());
//        }finally {
//            if (manager!=null) manager.close();
//        }
//        return isDeleted;
//    }

    @Override
    public List<AuditInfoEntity> fetchAuditsByUserId(int userId) {
        EntityManager manager = emf.createEntityManager();
        try {
            String jpql = "select a from AuditInfoEntity a where a.user.userId = :id";
            return manager.createQuery(jpql, AuditInfoEntity.class)
                    .setParameter("id", userId)
                    .getResultList();
        } finally {
            manager.close();
        }
    }

    @Override
    public boolean softDeleteById(int id) {
        System.out.println("softDeleteById method running in repo");

        boolean updated = false;
        EntityManager manager = null;
        try {
            manager = emf.createEntityManager();
            EntityTransaction transaction = manager.getTransaction();
            transaction.begin();

            UserEntity entity = manager.find(UserEntity.class, id);
            if (entity != null) {
                entity.setDeleted(true);
                manager.merge(entity);
                updated = true;
            }

            transaction.commit();
        } catch (PersistenceException e) {
            System.err.println("error in soft delete by id " + e.getMessage());
        } finally {
            if (manager != null) manager.close();
        }
        return updated;
    }

}