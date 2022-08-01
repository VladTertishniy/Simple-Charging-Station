package com.extrawest.simplechargingstationtertyshniy.service;

import com.extrawest.simplechargingstationtertyshniy.model.dto.request.UserRequestDTO;
import com.extrawest.simplechargingstationtertyshniy.model.dto.response.UserResponseDTO;

public interface AuthenticationService {
    UserResponseDTO register(UserRequestDTO userRequestDto);
}
