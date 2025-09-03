package com.mywork.usermanagement.service;

import com.mywork.usermanagement.dto.UserDTO;

import java.util.List;

public interface UserService {

    boolean save(UserDTO dto);

    List<UserDTO> getAll();

    UserDTO getById(int id);

    String updateDto(UserDTO dto);

    String deleteDto(int id);
}
