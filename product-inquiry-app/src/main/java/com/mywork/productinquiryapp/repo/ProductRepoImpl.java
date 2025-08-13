package com.mywork.productinquiryapp.repo;

import com.mywork.productinquiryapp.entity.ProductEntity;
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


    public static void emfClose(){
        if(emf.isOpen()) emf.close();
    }
}
