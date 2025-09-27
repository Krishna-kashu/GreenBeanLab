package com.mywork.dairy360.service;

import com.mywork.dairy360.dto.AdminDTO;
import com.mywork.dairy360.dto.SellerDTO;

import java.util.List;

public interface SellerService {
    boolean saveSeller(SellerDTO sellerDto, AdminDTO adminDTO);

    List<SellerDTO> getAllActiveSellers();

    SellerDTO getSellerById(Integer id);

    boolean softDeleteSeller(Integer id, String adminName);

    String checkMail(String email);

    List<SellerDTO> getAllActiveSellers(int page, int size);

    long countActiveSellers();
}
