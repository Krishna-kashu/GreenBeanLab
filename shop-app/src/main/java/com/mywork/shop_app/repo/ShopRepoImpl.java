package com.mywork.shop_app.repo;

import com.mywork.shop_app.entity.ShopEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class ShopRepoImpl implements ShopRepo{

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-work");
    @Override
    public boolean save(ShopEntity shopEntity) {
        System.out.println("save method in repo");

        if(shopEntity!=null){
            System.out.println("Repo : "+shopEntity);
            EntityManager manager= null;
            EntityTransaction transaction = null;
            try {
                manager= emf.createEntityManager();
                transaction = manager.getTransaction();

                transaction.begin();
                manager.merge(shopEntity);
                transaction.commit();
                System.out.println("saved Shop Entity: "+shopEntity);
            }catch (PersistenceException e){
                if(transaction.isActive()) transaction.rollback();
                System.out.println("error in save: "+e.getMessage());
            }finally {
                if(manager!=null) manager.close();
            }
        }

        return true;
    }
}