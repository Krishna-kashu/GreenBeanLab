package com.mywork.productinquiryapp.repo;

import com.mywork.productinquiryapp.dto.ProductDTO;
import com.mywork.productinquiryapp.entity.ProductEntity;

import java.util.List;

public interface ProductRepo {
    boolean save(ProductEntity entity);
    List<ProductEntity> fetchAll();
    ProductEntity findByID(int id);
}
