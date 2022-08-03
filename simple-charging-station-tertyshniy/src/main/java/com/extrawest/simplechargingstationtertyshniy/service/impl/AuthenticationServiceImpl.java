package com.extrawest.simplechargingstationtertyshniy.service.impl;

import com.extrawest.simplechargingstationtertyshniy.exception.ApiRequestException;
import com.extrawest.simplechargingstationtertyshniy.model.Role;
import com.extrawest.simplechargingstationtertyshniy.model.User;
import com.extrawest.simplechargingstationtertyshniy.model.dto.request.UserRequestDTO;
import com.extrawest.simplechargingstationtertyshniy.model.dto.response.UserResponseDTO;
import com.extrawest.simplechargingstationtertyshniy.model.mapper.UserMapper;
import com.extrawest.simplechargingstationtertyshniy.repository.UserRepository;
import com.extrawest.simplechargingstationtertyshniy.security.PasswordEncoderBean;
import com.extrawest.simplechargingstationtertyshniy.service.AuthenticationService;
import com.extrawest.simplechargingstationtertyshniy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoderBean passwordEncoder;
    private final UserService userService;

    @Override
    public UserResponseDTO register(UserRequestDTO userRequestDto) {
        if (userRepository.findByEmail(userRequestDto.getEmail()).isPresent()) {
            throw new ApiRequestException("Bad credentials: " + userRequestDto.getEmail());
        }
        return create(userRequestDto);
    }

    @Override
    public void login(String login, String password) throws AuthenticationException {
        User user = userService.getExistUser(login);
        String encodedPassword = passwordEncoder.passwordEncoder().encode(password);
        if (user.getPassword().equals(encodedPassword)) {
            throw new ApiRequestException("Bad credentials");
        }
    }

    private UserResponseDTO create(UserRequestDTO userRequestDto) {
        User user = userMapper.toModel(userRequestDto);
        user.setRole(Role.SELLER);
        user.setPassword(passwordEncoder.passwordEncoder().encode(userRequestDto.getPassword()));
        return userMapper.toDto(userRepository.save(user));
    }
}
