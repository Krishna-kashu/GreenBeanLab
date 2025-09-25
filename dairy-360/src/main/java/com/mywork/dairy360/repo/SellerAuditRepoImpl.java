package com.mywork.dairy360.repo;

import com.mywork.dairy360.entity.SellerAuditEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class SellerAuditRepoImpl implements SellerAuditRepo {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public SellerAuditRepoImpl() {
        System.out.println("SellerAuditRepoImplManual no-arg constructor");
    }

    @Override
    public boolean save(SellerAuditEntity auditEntity) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = entityManagerFactory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            if (auditEntity.getSellerAuditId() == null) {
                em.persist(auditEntity);
            } else {
                em.merge(auditEntity);
            }
            tx.commit();
            return true;
        } catch (PersistenceException e) {
            System.out.println("Error saving seller audit: " + e.getMessage());
            if (tx != null && tx.isActive()) tx.rollback();
            return false;
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }

    @Override
    public List<SellerAuditEntity> findAll() {
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            TypedQuery<SellerAuditEntity> query = em.createNamedQuery("findAllAudits", SellerAuditEntity.class);
            return query.getResultList();
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }

//    @Override
//    public SellerAuditEntity findById(Integer id) {
//        EntityManager em = null;
//        try {
//            em = entityManagerFactory.createEntityManager();
//            return em.find(SellerAuditEntity.class, id);
//        } finally {
//            if (em != null && em.isOpen()) em.close();
//        }
//    }

    @Override
    public boolean update(SellerAuditEntity auditEntity) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = entityManagerFactory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            em.merge(auditEntity);

            tx.commit();
            return true;
        } catch (PersistenceException e) {
            System.out.println("Error updating seller audit: " + e.getMessage());
            if (tx != null && tx.isActive()) tx.rollback();
            return false;
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }

//    @Override
//    public boolean deleteById(Integer id) {
//        EntityManager em = null;
//        EntityTransaction tx = null;
//        try {
//            em = entityManagerFactory.createEntityManager();
//            tx = em.getTransaction();
//            tx.begin();
//
//            SellerAuditEntity entity = em.find(SellerAuditEntity.class, id);
//            if (entity != null) {
//                em.remove(entity);
//                tx.commit();
//                return true;
//            }
//            tx.rollback();
//            return false;
//        } catch (PersistenceException e) {
//            System.out.println("Error deleting seller audit: " + e.getMessage());
//            if (tx != null && tx.isActive()) tx.rollback();
//            return false;
//        } finally {
//            if (em != null && em.isOpen()) em.close();
//        }
//    }

    @Override
    public SellerAuditEntity findBySellerId(Integer sellerId) {
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            TypedQuery<SellerAuditEntity> query = em.createNamedQuery(
                    "findBySellerId", SellerAuditEntity.class);
            query.setParameter("sellerId", sellerId);
            List<SellerAuditEntity> list = query.getResultList();
            return list.isEmpty() ? null : list.get(0);
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }


}
