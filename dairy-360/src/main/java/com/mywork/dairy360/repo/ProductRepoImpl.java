package com.mywork.dairy360.repo;

import com.mywork.dairy360.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepoImpl implements ProductRepo{

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public ProductRepoImpl() {
        System.out.println("ProductRepoImpl no-arg constructor");
    }

    @Override
    public boolean save(ProductEntity entity) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = entityManagerFactory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();

            if (entity.getProductId() == null) {
                em.persist(entity);
            } else {
                em.merge(entity);
            }

            tx.commit();
            return true;
        } catch (PersistenceException e) {
            System.out.println("Error saving product: " + e.getMessage());
            if (tx != null && tx.isActive()) tx.rollback();
            return false;
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }

    @Override
    public ProductEntity findById(Integer id) {
        EntityManager em = null;
        try {
            em = entityManagerFactory.createEntityManager();
            return em.find(ProductEntity.class, id);
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }

    @Override
    public boolean update(ProductEntity entity) {
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
            System.out.println("Error updating product: " + e.getMessage());
            if (tx != null && tx.isActive()) tx.rollback();
            return false;
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }


    @Override
    public List<ProductEntity> findAllActive(int page, int size) {
        EntityManager manager = null;
        try {
            manager = entityManagerFactory.createEntityManager();
            if (page < 1) page = 1;
            int firstResult = (page - 1) * size;

            TypedQuery<ProductEntity> query = manager.createNamedQuery(
                    "getAllActiveProducts",
                    ProductEntity.class
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
            Long count = manager.createNamedQuery(
                    "getCount",
                    Long.class
            ).getSingleResult();
            return count != null ? count : 0L;
        } finally {
            if (manager != null && manager.isOpen()) manager.close();
        }
    }

    public List<ProductEntity> findAllSellProducts() {
        EntityManager manager = null;
        try {
            manager = entityManagerFactory.createEntityManager();
            TypedQuery<ProductEntity> query = manager.createNamedQuery(
                    "getAllActiveProducts", ProductEntity.class
            );
            List<ProductEntity> allProducts = query.getResultList();
            return allProducts.stream()
                    .filter(p -> "Sell".equalsIgnoreCase(p.getType()))
                    .collect(Collectors.toList());
        } finally {
            if (manager != null && manager.isOpen()) manager.close();
        }
    }

}