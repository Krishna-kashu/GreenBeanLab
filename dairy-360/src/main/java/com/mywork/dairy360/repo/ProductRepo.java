package com.mywork.dairy360.repo;

import com.mywork.dairy360.entity.ProductEntity;

import java.util.List;

public interface ProductRepo {

    boolean save(ProductEntity entity);

    ProductEntity findById(Integer id);

    boolean update(ProductEntity entity);

    List<ProductEntity> findAllActive(int page, int size);

    long countActive();
}
