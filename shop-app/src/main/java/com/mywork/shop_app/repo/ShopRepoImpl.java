package com.mywork.shop_app.repo;

import com.mywork.shop_app.entity.ShopEntity;
import org.hibernate.QueryException;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;



@Repository
public class ShopRepoImpl implements ShopRepo {

    private static  EntityManagerFactory  emf = Persistence.createEntityManagerFactory("my-work");

    @Override
    public boolean save(ShopEntity shopEntity) {
        System.out.println("save method in repo");
        if (shopEntity != null) {
            EntityManager manager = null;
            EntityTransaction transaction = null;
            try {
                manager = emf.createEntityManager();
                transaction = manager.getTransaction();
                transaction.begin();
                manager.merge(shopEntity);
                transaction.commit();
            } catch (PersistenceException e) {
                if (transaction != null && transaction.isActive()) transaction.rollback();
                System.out.println("error in save: " + e.getMessage());
            } finally {
                if (manager != null) manager.close();
            }
        }
        return true;
    }

    @Override
    public List<ShopEntity> findAll() {
        List<ShopEntity> list = Collections.emptyList();
        EntityManager manager = null;
        try{
            manager = emf.createEntityManager();
            Query query = manager.createNamedQuery("findAll");

            list = query.getResultList();

        }catch (PersistenceException e){
            System.out.println("error from repo in findAll "+e.getMessage());
        }finally {
            if( manager!=null) manager.close();
        }
        return list;
    }

    @Override
    public ShopEntity findById(int id) {

        EntityManager manager = emf.createEntityManager();
        try {
            return manager.find(ShopEntity.class, id);
        }catch (PersistenceException e){
            System.out.println("error in findById"+e.getMessage());
        }finally {
            if(manager!= null) manager.close();
        }
        return null;
    }

    @Override
    public boolean existsByEmail(String email) {
        System.out.println("from repo Checking email in repo: " + email);
        EntityManager manager = null;

        try {
            manager = emf.createEntityManager();
            Query query = manager.createNamedQuery("emailExists");
            query.setParameter("email", email);

            ShopEntity entity = (ShopEntity) query.getSingleResult();
            System.out.println("Email : " + entity);
            if(entity!=null){
                return true;
            }

        } catch (PersistenceException e) {
            System.out.println("Error checking email: " + e.getMessage());

        } finally {
            if (manager != null) manager.close();
        }
        return false;
    }

    @Override
    public boolean updateEntity(ShopEntity entity) {

        System.out.println("update method in repo");
        System.out.println("Entity = "+entity);
        boolean isUpdated = false;
        EntityManager manager = null;

        try {
            manager = emf.createEntityManager();
            EntityTransaction transaction = manager.getTransaction();
            transaction.begin();
            Integer rows = manager.createNamedQuery("updateEntity")
                    .setParameter("shopName", entity.getShopName())
                    .setParameter("shopOwner", entity.getShopOwner())
                    .setParameter("totalBranch", entity.getTotalBranch())
                    .setParameter("ShopType", entity.getShopType())
                    .setParameter("email", entity.getEmail())
                    .setParameter("contact", entity.getContact())
                    .setParameter("id", entity.getShopId())
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
            System.out.println("error in deleteById  method "+e.getMessage());
        }finally {
            if(manager != null) manager.close();
        }
        return isDeleted;
    }
}



