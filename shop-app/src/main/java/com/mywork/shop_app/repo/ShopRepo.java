package com.mywork.shop_app.repo;

import com.mywork.shop_app.entity.ShopEntity;

public interface ShopRepo {
    boolean save(ShopEntity shopEntity);
}
