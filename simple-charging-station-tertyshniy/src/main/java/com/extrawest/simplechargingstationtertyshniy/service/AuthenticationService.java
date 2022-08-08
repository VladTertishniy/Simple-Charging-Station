package com.extrawest.simplechargingstationtertyshniy.service;

import com.extrawest.simplechargingstationtertyshniy.model.dto.request.UserRequestDTO;
import com.extrawest.simplechargingstationtertyshniy.model.dto.response.UserResponseDTO;
import org.springframework.security.core.AuthenticationException;

public interface AuthenticationService {
    UserResponseDTO register(UserRequestDTO userRequestDto);
    String login(String login, String password) throws AuthenticationException;
}
