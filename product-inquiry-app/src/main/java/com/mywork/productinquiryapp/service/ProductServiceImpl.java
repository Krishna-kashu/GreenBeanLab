package com.mywork.productinquiryapp.service;

import com.mywork.productinquiryapp.dto.ProductDTO;
import com.mywork.productinquiryapp.entity.ProductEntity;
import com.mywork.productinquiryapp.repo.ProductRepoImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepoImpl repo;
    @Override
    public boolean valid(ProductDTO dto) {
        System.out.println("running valid method in ProductServiceImpl");

        if(dto==null){
            System.out.println("DTO is null");
            return false;
        }
        if (dto.getFullName()==null || dto.getFullName().trim().isEmpty()){
            System.out.println("Name is invalid");
            return false;
        }
        if(dto.getEmail() == null || !dto.getEmail().contains("@")){
            System.out.println("Email is invalid");
            return  false;
        }

        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductName(dto.getProductName());
        productEntity.setFullName(dto.getFullName());
        productEntity.setEmail(dto.getEmail());
        productEntity.setPhone(dto.getPhone());
        productEntity.setInquiryType(dto.getInquiryType());
        productEntity.setMessage(dto.getMessage());

        repo.save(productEntity);
        return true;
    }

    @Override
    public List<ProductDTO> getAll() {
        System.out.println("getAll method in service");

        List<ProductDTO> productDTOList = null;
        List<ProductEntity> productEntities = repo.fetchAll();

        productDTOList = productEntities.stream().map(e->{ProductDTO productDTO = new ProductDTO();
            productDTO.setId(e.getId());
            productDTO.setFullName(e.getFullName());
            productDTO.setEmail(e.getEmail());
            productDTO.setPhone(e.getPhone());
            productDTO.setProductName(e.getProductName());
            productDTO.setInquiryType(e.getInquiryType());
            productDTO.setMessage(e.getMessage());
            return  productDTO;
        }).collect(Collectors.toList());

        productEntities.forEach(System.out::println);
        return productDTOList;
    }

    @Override
    public ProductDTO getById(int id) {

        ProductDTO dto = new ProductDTO();
        ProductEntity entity = repo.findByID(id);
//        dto.setId(entity.getId());
//        dto.setFullName(entity.getFullName());
//        dto.setEmail(entity.getEmail());
//        dto.setPhone(entity.getPhone());
//        dto.setProductName(entity.getProductName());
//        dto.setInquiryType(entity.getInquiryType());
//        dto.setMessage(entity.getMessage());

        if (entity!=null) BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    @Override
    public String updateDto(ProductDTO dto) {
        System.out.println("updateDto method in service "+"\t dto in updateDto: "+dto);
        ProductEntity entity = new ProductEntity();

        BeanUtils.copyProperties(dto, entity);
        boolean updated= repo.updateEntity(entity);
        if (updated){
            return "updated";
        }return "update failed";

    }

    @Override
    public String deleteDto(int id) {
        System.out.println("deleteDto method in service");
        boolean deleted = repo.deleteById(id);
        if (deleted){
            return "deleted";
        }
        return "delete failed";
    }

    @Override
    public boolean checkMail(String email) {
        System.out.println("checkEmail method in service");
        return repo.checkMail(email);
    }


}
