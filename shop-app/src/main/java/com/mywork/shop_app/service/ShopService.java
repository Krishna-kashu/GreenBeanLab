package com.mywork.shop_app.service;

import com.mywork.shop_app.dto.ShopDTO;

import java.util.List;

public interface ShopService {
    boolean valid(ShopDTO shopDTO);
    List<ShopDTO> getAll();
    ShopDTO getById(int id);
    boolean mailExits(String email);

    String updateDto(ShopDTO dto);

    String deleteDto(int id);
}
