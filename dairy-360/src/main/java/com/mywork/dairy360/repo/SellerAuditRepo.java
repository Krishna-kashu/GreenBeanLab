package com.mywork.dairy360.repo;

import com.mywork.dairy360.entity.SellerAuditEntity;

import java.util.List;

public interface SellerAuditRepo {

    boolean save(SellerAuditEntity auditEntity);

    List<SellerAuditEntity> findAll();

//    SellerAuditEntity findById(Integer id);

    boolean update(SellerAuditEntity auditEntity);

//    boolean deleteById(Integer id);

    SellerAuditEntity findBySellerId(Integer sellerId);
}
