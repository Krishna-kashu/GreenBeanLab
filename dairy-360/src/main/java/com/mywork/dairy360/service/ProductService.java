package com.mywork.dairy360.service;

import com.mywork.dairy360.dto.AdminDTO;
import com.mywork.dairy360.dto.ProductDTO;
import java.util.List;

public interface ProductService {

    boolean saveProduct(ProductDTO dto, AdminDTO adminDTO);

    ProductDTO getProductById(Integer id);

    boolean updateProduct(ProductDTO dto, AdminDTO adminDTO);

    boolean softDeleteProduct(Integer id, AdminDTO adminDTO);

    List<ProductDTO> getAllActiveProducts(int page, int size);

    long countActiveProducts();

    List<ProductDTO> getSellProducts();
}
