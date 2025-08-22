package com.mywork.shop_app.service;

import com.mywork.shop_app.dto.ShopDTO;
import com.mywork.shop_app.entity.ShopEntity;
import com.mywork.shop_app.repo.ShopRepoImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<ShopDTO> getAll() {
        System.out.println("get All method is running in serviceImpl");

        List<ShopDTO> shopDTOList = null;
        List<ShopEntity> shopEntityList = shopRepo.findAll();

        shopDTOList = shopEntityList.stream().map(e -> {
            ShopDTO dto = new ShopDTO();
            dto.setShopId(e.getShopId());
            dto.setShopName(e.getShopName());
            dto.setShopOwner(e.getShopOwner());
            dto.setTotalBranch(e.getTotalBranch());
            dto.setShopType(e.getShopType());
            dto.setEmail(e.getEmail());
            dto.setContact(e.getContact());
            return dto;
        }).collect(Collectors.toList());

        shopEntityList.forEach(e -> System.out.println(e));

        return shopDTOList;
    }

    @Override
    public ShopDTO getById(int id) {
        ShopDTO dto = new ShopDTO();
        ShopEntity entity = shopRepo.findById(id);
        dto.setShopId(entity.getShopId());
        dto.setShopName(entity.getShopName());
        dto.setShopOwner(entity.getShopOwner());
        dto.setTotalBranch(entity.getTotalBranch());
        dto.setShopType(entity.getShopType());
        dto.setEmail(entity.getEmail());
        dto.setContact(entity.getContact());

        return dto;
    }

    @Override
    public boolean mailExits(String email) {
        System.out.println("from service Checking if email exists: " + email);
        return shopRepo.existsByEmail(email);
    }

    @Override
    public String updateDto(ShopDTO dto) {
        System.out.println("updateDto method in service "+"\t dto in updateDto: "+dto);
        ShopEntity entity = new ShopEntity();

        BeanUtils.copyProperties(dto, entity);
        boolean updated= shopRepo.updateEntity(entity);
        if (updated){
            return "updated";
        }return "update failed";

    }

    @Override
    public String deleteDto(int id) {
        System.out.println("deleteDto method in service");
        boolean deleted = shopRepo.deleteById(id);
        if (deleted){
            return "deleted";
        }
        return "delete failed";
    }

}
