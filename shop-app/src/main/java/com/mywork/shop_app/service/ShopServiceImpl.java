package com.mywork.shop_app.service;

import com.mywork.shop_app.dto.ShopDTO;
import com.mywork.shop_app.entity.ShopEntity;
import com.mywork.shop_app.repo.ShopRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements  ShopService{

    @Autowired
    ShopRepoImpl shopRepo;

    @Override
    public boolean valid(ShopDTO shopDTO) {
        System.out.println("Running validate in FanServiceImpl");

        if (shopDTO == null) return false;

        boolean valid = true;

        if (shopDTO.getShopName() == null || shopDTO.getShopName().length() < 2)
            valid = false;
        if (shopDTO.getShopOwner() == null || shopDTO.getShopOwner().length() < 2)
            valid = false;
        if (shopDTO.getTotalBranch() == null || shopDTO.getTotalBranch() < 2 || shopDTO.getTotalBranch() > 200) {
            System.out.println("TotalBranch should be between 2 to 200: " );
            valid = false;
        }

        ShopEntity shopEntity = new ShopEntity();
        shopEntity.setShopName(shopDTO.getShopName());
        shopEntity.setShopOwner(shopDTO.getShopOwner());
        shopEntity.setShopType(shopDTO.getShopType());
        shopEntity.setEmail(shopDTO.getEmail());
        shopEntity.setTotalBranch(shopDTO.getTotalBranch());
        shopEntity.setContact(shopDTO.getContact());

        shopRepo.save(shopEntity);
        return valid;
    }
}
