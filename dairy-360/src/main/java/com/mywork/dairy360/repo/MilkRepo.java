package com.mywork.dairy360.repo;

import com.mywork.dairy360.entity.CollectMilkEntity;

public interface MilkRepo {

    boolean save(CollectMilkEntity entity);

    CollectMilkEntity findByPhoneNumber(String phoneNumber);

    boolean updateTotalAmountById(double totalAmount, int id);
}
