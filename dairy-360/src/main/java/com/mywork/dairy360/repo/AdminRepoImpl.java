package com.mywork.dairy360.repo;

import com.mywork.dairy360.entity.AdminEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class AdminRepoImpl implements AdminRepo {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public AdminRepoImpl() {
        System.out.println("AdminRepoIml no-arg constructor");
    }

    @Override
    public boolean save(AdminEntity adminEntity) {
        System.out.println("save method in AdminRepoIml");
        System.out.println("AdminRepoIml data: " + adminEntity);
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            if (adminEntity.getAdminId() == null) {
                entityManager.persist(adminEntity);
            } else {
                entityManager.merge(adminEntity);
            }
            entityTransaction.commit();
            return true;
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            if (entityTransaction != null) {
                entityTransaction.rollback();
                System.out.println("roll backed");
            }
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return false;
    }

    @Override
    public AdminEntity getPasswordByEmail(String email) {
        System.out.println("getPasswordByEmail method in repository");
        EntityManager entityManager = null;
        AdminEntity entity = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entity = (AdminEntity) entityManager.createNamedQuery("getPasswordByEmail").setParameter("email", email).getSingleResult();
            return entity;
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
                System.out.println("EntityManager is closed");
            }
        }
        return entity;
    }

    @Override
    public boolean updateAdminProfileByEmail(String email, String adminName, String phoneNumber) {
        System.out.println("updateAdminProfileByEmail method in repository");
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;
        AdminEntity existingEntity;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            existingEntity = (AdminEntity) entityManager.createNamedQuery("getPasswordByEmail").setParameter("email", email).getSingleResult();
            if (existingEntity == null) {
                return false;
            }
            existingEntity.setAdminName(adminName);
            existingEntity.setPhoneNumber(phoneNumber);
            entityManager.merge(existingEntity);
            entityTransaction.commit();
            return true;
        } catch (PersistenceException e) {
            System.err.println("error in repo impl " + e.getMessage());
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
        return false;
    }

    @Override
    public AdminEntity getByResetToken(String token) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM AdminEntity a WHERE a.resetToken = :token", AdminEntity.class)
                    .setParameter("token", token)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public AdminEntity getAdminEntityByEmail(String email) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            String jpql = "SELECT a FROM AdminEntity a WHERE a.email = :email";
            TypedQuery<AdminEntity> query = entityManager.createQuery(jpql, AdminEntity.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public boolean updateProfileImage(String email, String imagePath) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            AdminEntity admin = em.createQuery("SELECT a FROM AdminEntity a WHERE a.email = :email", AdminEntity.class)
                    .setParameter("email", email)
                    .getSingleResult();
            if (admin == null) return false;
            admin.setProfileImagePath(imagePath);
            em.merge(admin);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            return false;
        } finally {
            em.close();
        }
    }


}