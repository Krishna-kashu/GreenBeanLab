package com.mywork.productinquiryapp.service;

import com.mywork.productinquiryapp.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    boolean valid(ProductDTO dto);
    List<ProductDTO> getAll();
    ProductDTO getById(int id);
}
