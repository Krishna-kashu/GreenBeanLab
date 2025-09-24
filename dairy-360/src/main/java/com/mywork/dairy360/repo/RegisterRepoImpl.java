package com.mywork.dairy360.repo;

import com.mywork.dairy360.entity.RegisterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.List;

@Repository
public class RegisterRepoImpl implements RegisterRepo {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public boolean save(RegisterEntity registerEntity) {
        System.out.println("save method in RegisterRepoImpl");
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(registerEntity);
            entityTransaction.commit();
            return true;
        } catch (PersistenceException e) {
            System.out.println("Error while saving: " + e.getMessage());
            if (entityTransaction != null) {
                entityTransaction.rollback();
                System.out.println("Transaction rolled back");
            }
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return false;
    }

    @Override
    public String checkMail(String email) {
        System.out.println("check mail method in repository");
        EntityManager manager = null;

        try {
            manager = entityManagerFactory.createEntityManager();
            List<RegisterEntity> resultList = manager
                    .createNamedQuery("checkMail", RegisterEntity.class)
                    .setParameter("email", email)
                    .getResultList();

            return resultList.isEmpty() ? null : resultList.get(0).getEmail();

        } catch (PersistenceException e) {
            System.err.println("Error in checkMail: " + e.getMessage());
            return null;
        } finally {
            if (manager != null) manager.close();
        }
    }

    @Override
    public RegisterEntity getUserByEmail(String email) {
        EntityManager manager = null;
        try {
            manager = entityManagerFactory.createEntityManager();
            List<RegisterEntity> resultList = manager
                    .createNamedQuery("checkMail", RegisterEntity.class)
                    .setParameter("email", email)
                    .getResultList();

            return resultList.isEmpty() ? null : resultList.get(0);
        } catch (PersistenceException e) {
            System.err.println("Error in getUserByEmail: " + e.getMessage());
            return null;
        } finally {
            if (manager != null) manager.close();
        }
    }

    @Override
    public RegisterEntity findByEmail(String email) {
        return getUserByEmail(email);
    }

    @Override
    public RegisterEntity findByResetToken(String token) {
        EntityManager manager = null;
        try {
            manager = entityManagerFactory.createEntityManager();
            List<RegisterEntity> resultList = manager
                    .createNamedQuery("findByMail", RegisterEntity.class)
                    .setParameter("token", token)
                    .getResultList();

            return resultList.isEmpty() ? null : resultList.get(0);
        } catch (PersistenceException e) {
            System.err.println("Error in findByResetToken: " + e.getMessage());
            return null;
        } finally {
            if (manager != null) manager.close();
        }
    }

    @Override
    public boolean update(RegisterEntity entity) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(entity);
            transaction.commit();
            return true;
        } catch (PersistenceException e) {
            System.err.println("Error while updating: " + e.getMessage());
            if (transaction != null) transaction.rollback();
            return false;
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}