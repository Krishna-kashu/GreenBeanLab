package com.mywork.dairy360.repo;

import com.mywork.dairy360.entity.SellerEntity;

import java.util.List;

public interface SellerRepo {
    boolean save(SellerEntity entity);

    List<SellerEntity> findAllActive();

    SellerEntity findById(Integer id);

    boolean update(SellerEntity entity);

//    boolean softDelete(Integer id);

    boolean existsByEmail(String email);

    String checkMail(String email);

    List<SellerEntity> findAllActive(int page, int size);

    long countActive();
}
