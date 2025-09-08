package com.mywork.onlinelearning.repo;

import com.mywork.onlinelearning.entity.LearnerAuditEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import java.util.List;

@Repository
public class AuditRepoImpl implements AuditRepo{

    @Autowired
    private EntityManagerFactory emf;

    @Override
    public List<LearnerAuditEntity> findAllAudits() {
        System.out.println("findAllAudits method in AuditRepoImpl");
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                    "SELECT a FROM LearnerAuditEntity a JOIN FETCH a.learner",
                    LearnerAuditEntity.class
            ).getResultList();
        }catch (PersistenceException e) {
            System.err.println("error in findAllAudits : " + e.getMessage());
            return null;
        }finally {
            em.close();
        }
    }

    @Override
    public void save(LearnerAuditEntity audit) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(audit);
            tx.commit();
        } catch (PersistenceException e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
