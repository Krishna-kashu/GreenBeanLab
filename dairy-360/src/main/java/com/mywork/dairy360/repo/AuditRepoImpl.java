package com.mywork.dairy360.repo;

import com.mywork.dairy360.entity.AdminEntity;
import com.mywork.dairy360.entity.AdminAuditEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class AuditRepoImpl implements AuditRepo{


    @Autowired
    private EntityManagerFactory entityManagerFactory;


    @Override
    public boolean save(AdminAuditEntity auditEntity) {
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
    public List<AdminAuditEntity> findAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<AdminAuditEntity> query = em.createNamedQuery("getAll", AdminAuditEntity.class);
            return query.getResultList();
        } finally {
            if (em.isOpen()) em.close();
        }
    }

    @Override
    public AdminAuditEntity findTopByAdminOrderByLoginTimeDesc(AdminEntity admin) {

        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            TypedQuery<AdminAuditEntity> query = entityManager.createNamedQuery("getAll", AdminAuditEntity.class);
            query.setParameter("admin", admin);
            query.setMaxResults(1);

            AdminAuditEntity entity = query.getSingleResult();

            System.out.println("entity in findTop by admin order: "+entity);

            int rows=entityManager.createNamedQuery("updateLogoutTime")
                    .setParameter("logoutTime", LocalDateTime.now())
                    .setParameter("id",entity.getId()).executeUpdate();
            entityManager.getTransaction().commit();
            System.out.println("rows affected: "+rows);

            if(rows==1){
                return entity;
            }
            return null;

        } catch (NoResultException e) {
            return null;
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}
