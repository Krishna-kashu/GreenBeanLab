package com.mywork.bugreportsubmission.repo;

import com.mywork.bugreportsubmission.dto.BugDTO;
import com.mywork.bugreportsubmission.entity.BugEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Repository
public class BugRepoImpl implements BugRepo{

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-work");

    @Override
    public boolean save(BugEntity entity) {

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
    public List<BugEntity> findAll() {
        List<BugEntity> list = Collections.emptyList();
        EntityManager manager = null;
        try{
            manager = emf.createEntityManager();
            Query query = manager.createNamedQuery("findAll");

            list = query.getResultList();

        }catch (PersistenceException e){
            System.out.println("error in findAll "+e.getMessage());
        }finally {
           if( manager!=null) manager.close();
        }

        return list;
    }
}
