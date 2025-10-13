package com.mywork.dairy360.repo;


import com.mywork.dairy360.entity.CollectMilkEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class MilkRepoImpl implements MilkRepo{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean save(CollectMilkEntity entity) {
        System.out.println("save method in milkRepo");
        entityManager.persist(entity);
        return true;
    }

    @Override
    public CollectMilkEntity findByPhoneNumber(String phoneNumber) {
        try {
            Query query = entityManager.createQuery("SELECT m FROM CollectMilkEntity m WHERE m.phoneNumber = :ph");
            query.setParameter("ph", phoneNumber);
            return (CollectMilkEntity) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("No record found for phone: " + phoneNumber);
            return null;
        }
    }

    @Override
    public boolean updateTotalAmountById(double totalAmount, int id) {
        try {
            Query query = entityManager.createQuery(
                    "UPDATE CollectMilkEntity m SET m.totalAmount = :amt WHERE m.id = :id");
            query.setParameter("amt", totalAmount);
            query.setParameter("id", id);
            int updated = query.executeUpdate();
            return updated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}