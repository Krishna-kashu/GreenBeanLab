package com.mywork.dairy360.repo;

import com.mywork.dairy360.entity.SellerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

import javax.persistence.*;

@Repository
public class SellerRepoImpl implements SellerRepo {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public SellerRepoImpl() {
        System.out.println("SellerRepoImpl no-arg constructor");
    }

    @Override
    public boolean save(SellerEntity entity) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = entityManagerFactory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            if (entity.getSellerId() == null) {
                em.persist(entity);
            } else {
                em.merge(entity);
            }

            tx.commit();
            return true;
        } catch (PersistenceException e) {
            System.out.println("Error saving seller: " + e.getMessage());
            if (tx != null && tx.isActive()) tx.rollback();
            return false;
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }

    @Override
    public List<SellerEntity> findAllActive() {
        System.out.println("findAllActive");
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            TypedQuery<SellerEntity> query = em.createNamedQuery("findAll", SellerEntity.class);
            return query.getResultList();
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }

    @Override
    public SellerEntity findById(Integer id) {
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            return em.find(SellerEntity.class, id);
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }

    @Override
    public boolean update(SellerEntity entity) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = entityManagerFactory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            em.merge(entity);

            tx.commit();
            return true;
        } catch (PersistenceException e) {
            System.out.println("Error updating seller: " + e.getMessage());
            if (tx != null && tx.isActive()) tx.rollback();
            return false;
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }

//    @Override
//    public boolean softDelete(Integer id) {
//        EntityManager em = null;
//        EntityTransaction tx = null;
//        try {
//            em = entityManagerFactory.createEntityManager();
//            tx = em.getTransaction();
//            tx.begin();
//
//            SellerEntity entity = em.find(SellerEntity.class, id);
//            if (entity != null) {
//                entity.setActive(false);
//                em.merge(entity);
//                tx.commit();
//                return true;
//            }
//            tx.rollback();
//            return false;
//        } catch (PersistenceException e) {
//            System.out.println("Error deleting seller: " + e.getMessage());
//            if (tx != null && tx.isActive()) tx.rollback();
//            return false;
//        } finally {
//            if (em != null && em.isOpen()) em.close();
//        }
//    }

    @Override
    public boolean existsByEmail(String email) {
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            Long count = em.createNamedQuery("byMail", Long.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return count > 0;
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }

    public String checkMail(String email) {
        System.out.println("checkMail method in repo");
        EntityManager manager = null;

        try {
            manager = entityManagerFactory.createEntityManager();
            List<SellerEntity> sellerEntities = manager.createNamedQuery("checkEmail", SellerEntity.class)
                    .setParameter("email", email.trim())
                    .getResultList();

            return sellerEntities.isEmpty() ? null : sellerEntities.get(0).getEmail();
        } catch (PersistenceException e) {
            System.out.println("Error in check mail: " + e.getMessage());
            return null;
        } finally {
            if (manager != null) manager.close();
        }
    }

    //pagination
    @Override
    public List<SellerEntity> findAllActive(int page, int size) {
        EntityManager manager = null;
        try {
            manager = entityManagerFactory.createEntityManager();
            if (page < 1) page = 1;
            int firstResult = (page - 1) * size;

            TypedQuery<SellerEntity> query = manager.createQuery(
                    "SELECT s FROM SellerEntity s WHERE s.active = true ORDER BY s.sellerId DESC",
                    SellerEntity.class
            );
            query.setFirstResult(firstResult);
            query.setMaxResults(size);
            return query.getResultList();
        } finally {
            if (manager != null && manager.isOpen()) manager.close();
        }
    }

    @Override
    public long countActive() {
        EntityManager manager = null;
        try {
            manager = entityManagerFactory.createEntityManager();
            Long count = manager.createQuery(
                    "SELECT COUNT(s) FROM SellerEntity s WHERE s.active = true",
                    Long.class
            ).getSingleResult();
            return count != null ? count : 0L;
        } finally {
            if (manager != null && manager.isOpen()) manager.close();
        }
    }
}