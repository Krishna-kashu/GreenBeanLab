package com.mywork.productinquiryapp.repo;

import com.mywork.productinquiryapp.entity.ProductEntity;

import java.util.List;

public interface ProductRepo {
    boolean save(ProductEntity entity);
    List<ProductEntity> fetchAll();
    ProductEntity findByID(int id);
    boolean updateEntity(ProductEntity entity);
    boolean deleteById(int id);
    boolean checkMail(String email);
}