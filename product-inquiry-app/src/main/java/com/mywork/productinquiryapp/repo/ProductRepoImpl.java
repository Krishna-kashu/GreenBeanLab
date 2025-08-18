package com.mywork.productinquiryapp.repo;

import com.mywork.productinquiryapp.entity.ProductEntity;
import org.hibernate.QueryException;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepoImpl implements ProductRepo{

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-work");

    @Override
    public boolean save(ProductEntity entity) {
        System.out.println("save method in ProductRepoImpl");

        if (entity!=null) {
            System.out.println("Repo: " + entity);
            EntityManager manager = null;
            EntityTransaction transaction = null;
            try {
                manager = emf.createEntityManager();
                transaction = manager.getTransaction();

                transaction.begin();
                manager.merge(entity);
                transaction.commit();
                System.out.println("saved entity: "+entity);

            }catch (PersistenceException e){
                if (transaction.isActive()) transaction.rollback();
                System.out.println("error in save in repo"+e.getMessage());
            }finally {
                if (manager!=null) manager.close();
            }
        }
        return true;
    }

    @Override
    public List<ProductEntity> fetchAll() {
        List<ProductEntity> list = Collections.emptyList();
        EntityManager manager = null;

        try {
            manager = emf.createEntityManager();
            Query query = manager.createNamedQuery("fetchAll");
            list = query.getResultList();
        }catch (PersistenceException e){
            e.getMessage();
        }finally {
            if(manager!=null) manager.close();
        }
        return list;
    }
    @Override
    public ProductEntity findByID(int id) {
        EntityManager manager = emf.createEntityManager();
        try {
            return manager.find(ProductEntity.class, id);
        }catch (PersistenceException e){
            System.out.println("error in findById"+e.getMessage());
        }finally {
            if(manager!= null) manager.close();
        }
        return null;
    }

    @Override
    public boolean updateEntity(ProductEntity entity) {

        System.out.println("update method in repo");
        System.out.println("Entity = "+entity);
        boolean isUpdated = false;
        EntityManager manager = null;

        try {
            manager = emf.createEntityManager();
            EntityTransaction transaction = manager.getTransaction();
            transaction.begin();
            Integer rows = manager.createNamedQuery("updateEntity")
                    .setParameter("fullName", entity.getFullName())
                    .setParameter("email", entity.getEmail())
                    .setParameter("phone", entity.getPhone())
                    .setParameter("productName", entity.getProductName())
                    .setParameter("message", entity.getMessage())
                    .setParameter("inquiryType", entity.getInquiryType())
                    .setParameter("id", entity.getId())
                    .executeUpdate();
            if(rows>0 )isUpdated = true;
            transaction.commit();
        }catch (NoResultException | QueryException e){
            System.out.println("error in updateEntity method "+e.getMessage());
        }finally {
            if(manager!=null) manager.close();
        }
        return isUpdated;
    }

    @Override
    public boolean deleteById(int id) {
        System.out.println("deleteById method is running in repo");
        boolean isDeleted = false;
        EntityManager manager = null;
        try {
            manager = emf.createEntityManager();
            EntityTransaction transaction = manager.getTransaction();
            transaction.begin();

            int row = manager.createNamedQuery("deleteById")
                    .setParameter("id", id).executeUpdate();
            if(row>0){
                isDeleted = true;
                transaction.commit();
            }
        }catch (NoResultException | QueryException e){
            System.out.println("error in delete method "+e.getMessage());
        }finally {
            if(manager != null) manager.close();
        }
        return isDeleted;
    }

    @Override
    public boolean checkMail(String email) {
        System.out.println("check mail method in repo, email:  "+email);
        EntityManager manager = null;
        try {
            manager = emf.createEntityManager();
            Query query = manager.createNamedQuery("checkMail")
                    .setParameter("email", email);

            ProductEntity entity = (ProductEntity) query.getSingleResult();
            System.out.println("email already exists");
            if(entity!= null){
                return  true;
            }
        }catch ( PersistenceException e){
            System.out.println("error in checkMail"+e.getMessage());
        }finally {
            if(manager != null) manager.close();
        }
        return false;
    }

    public static void emfClose(){
        if(emf.isOpen()) emf.close();
    }
}
