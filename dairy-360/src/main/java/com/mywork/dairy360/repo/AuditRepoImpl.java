package com.mywork.dairy360.repo;

import com.mywork.dairy360.entity.AdminEntity;
import com.mywork.dairy360.entity.AuditEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class AuditRepoImpl implements AuditRepo{


    @Autowired
    private EntityManagerFactory entityManagerFactory;


    @Override
    public boolean save(AuditEntity auditEntity) {
        System.out.println("save method in AuditRepoImpl");
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            if (auditEntity.getId() == null) {
                entityManager.persist(auditEntity);
            } else {
                entityManager.merge(auditEntity);
            }

            transaction.commit();
            return true;
        } catch (PersistenceException e) {
            System.out.println(e.getMessage());
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
                System.out.println("Transaction rolled back");
            }
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
                System.out.println("EntityManager closed");
            }
        }
        return false;
    }

    @Override
    public List<AuditEntity> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            String jpql = "SELECT a FROM AuditEntity a ORDER BY a.loginTime DESC";
            TypedQuery<AuditEntity> query = em.createQuery(jpql, AuditEntity.class);
            return query.getResultList();
        } finally {
            if (em.isOpen()) em.close();
        }
    }

    @Override
    public AuditEntity findTopByAdminOrderByLoginTimeDesc(AdminEntity admin) {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            String jpql = "SELECT a FROM AuditEntity a WHERE a.admin = :admin ORDER BY a.loginTime DESC";
            TypedQuery<AuditEntity> query = entityManager.createQuery(jpql, AuditEntity.class);
            query.setParameter("admin", admin);
            query.setMaxResults(1);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}
