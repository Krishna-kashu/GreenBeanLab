package com.mywork.dairy360.service;

import com.mywork.dairy360.dto.AdminDTO;
import com.mywork.dairy360.dto.ProductDTO;
import com.mywork.dairy360.entity.ProductEntity;
import com.mywork.dairy360.repo.ProductRepoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepoImpl productRepo;

    private static final Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);

    public ProductServiceImpl(){
        log.info("no-arg constructor of ProductRepoImpl");
    }

    @Override
    public boolean saveProduct(ProductDTO dto, AdminDTO adminDTO) {
        log.info("saveProduct method in ProductServiceImpl");
        try {
            ProductEntity entity = new ProductEntity();
            BeanUtils.copyProperties(dto, entity);

            entity.setActive(true);
            entity.setCreatedBy(adminDTO.getAdminName());
            entity.setCreatedOn(LocalDateTime.now().toString());

            return productRepo.save(entity);
        } catch (Exception e) {
            System.out.println("Error saving product: " + e.getMessage());
            return false;
        }
    }

    @Override
    public ProductDTO getProductById(Integer id) {
        log.info("getProductById method in ProductServiceImpl");
        ProductEntity entity = productRepo.findById(id);
        if (entity == null) return null;
        ProductDTO dto = new ProductDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    @Override
    public boolean updateProduct(ProductDTO dto,  AdminDTO adminDTO) {

        System.out.println("updateProduct method from ProductServiceImpl");
        try {
            ProductEntity entity = productRepo.findById(dto.getProductId());
            if (entity == null) return false;

            entity.setProductName(dto.getProductName());
            entity.setPrice(dto.getPrice());
            entity.setType(dto.getType());
            entity.setUpdatedBy(adminDTO.getAdminName());
            entity.setUpdatedOn(LocalDateTime.now().toString());
            entity.setActive(dto.getActive());

            return productRepo.update(entity);
        } catch (Exception e) {
            System.out.println("Error updating product: " + e.getMessage());
            return false;
        }
    }

    @Override public boolean softDeleteProduct(Integer id, AdminDTO adminDTO) {
        System.out.println("softDeleteProduct method from the ProductServiceImpl ");
        ProductEntity entity = productRepo.findById(id);
        if (entity != null) {
            entity.setActive(false);
            entity.setUpdatedBy(adminDTO.getAdminName());
            entity.setUpdatedOn(LocalDateTime.now().toString());
            return productRepo.update(entity);
        }
        return false;
    }

    @Override
    public List<ProductDTO> getAllActiveProducts(int page, int size) {
        log.info("getAllActiveProducts method in ProductServiceImpl");
        return productRepo.findAllActive(page, size).stream().map(entity -> {
            ProductDTO dto = new ProductDTO();
            BeanUtils.copyProperties(entity, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public long countActiveProducts() {
        log.info("countActiveProducts method in ProductServiceImpl");
        return productRepo.countActive();
    }

    @Override
    public List<ProductDTO> getSellProducts() {
        return productRepo.findAllSellProducts().stream().map(entity -> {
            ProductDTO dto = new ProductDTO();
            BeanUtils.copyProperties(entity, dto);
            return dto;
        }).collect(Collectors.toList());
    }


}
