package com.extrawest.simplechargingstationtertyshniy.service;

import com.extrawest.simplechargingstationtertyshniy.model.User;
import com.extrawest.simplechargingstationtertyshniy.model.dto.request.UserRequestDTO;
import com.extrawest.simplechargingstationtertyshniy.model.dto.response.UserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {
    User create(User user);

    Page<UserResponseDTO> getAll(Pageable pageable);

    UserResponseDTO getById(Long userId);

    void delete(String email, Long userId);

    UserResponseDTO update(String email, Long userId, UserRequestDTO userRequestDto);

    User getExistUser(String email);
}
