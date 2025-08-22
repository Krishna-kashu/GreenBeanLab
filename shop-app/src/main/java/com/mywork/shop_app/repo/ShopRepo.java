package com.mywork.shop_app.repo;

import com.mywork.shop_app.entity.ShopEntity;

import java.util.List;

public interface ShopRepo {
    boolean save(ShopEntity shopEntity);
    List<ShopEntity> findAll();
    ShopEntity findById(int id);
    boolean existsByEmail(String email);
    boolean updateEntity(ShopEntity entity);
    boolean deleteById(int id);
}
