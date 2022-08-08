package com.extrawest.simplechargingstationtertyshniy.service.impl;

import com.extrawest.simplechargingstationtertyshniy.exception.ApiRequestException;
import com.extrawest.simplechargingstationtertyshniy.model.Role;
import com.extrawest.simplechargingstationtertyshniy.model.User;
import com.extrawest.simplechargingstationtertyshniy.model.dto.request.UserRequestDTO;
import com.extrawest.simplechargingstationtertyshniy.model.dto.response.UserResponseDTO;
import com.extrawest.simplechargingstationtertyshniy.model.mapper.UserMapper;
import com.extrawest.simplechargingstationtertyshniy.repository.UserRepository;
import com.extrawest.simplechargingstationtertyshniy.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public Page<UserResponseDTO> getAll(Pageable pageable) {
        Page<UserResponseDTO> all = userRepository.findAll(pageable).map(userMapper::toDto);
        return new PageImpl<>(all.getContent(), pageable, all.getTotalElements());
    }

    @Override
    public UserResponseDTO getById(Long userId) {
        User user = getByUserId(userId);
        return userMapper.toDto(user);
    }

    @Override
    public void delete(String email, Long userId) {
        User principalUser = getExistUser(email);
        User forDelete = getByUserId(userId);
        if (Role.BUYER == principalUser.getRole() || Role.SELLER == principalUser.getRole()) {
            if (principalUser.getEmail().equals(forDelete.getEmail())) {
                userRepository.delete(getByUserId(userId));
                return;
            }
            throw new ApiRequestException("No rights");
        }
        userRepository.delete(getByUserId(userId));
    }

    @Override
    public UserResponseDTO update(String email, Long userId, UserRequestDTO userRequestDto) {
        User principalUser = getExistUser(email);
        User updatedUser = getUpdatedUser(userId, userRequestDto);
        if (Role.BUYER == principalUser.getRole() || Role.SELLER == principalUser.getRole()) {
            if (principalUser.getEmail().equals(userRequestDto.getEmail())) {
                return userMapper.toDto(create(updatedUser));
            }
            throw new ApiRequestException("No rights");
        }
        if (Role.MANAGER == principalUser.getRole() && Role.ADMIN == updatedUser.getRole()) {
            throw new ApiRequestException("No rights");
        }
        return userMapper.toDto(create(updatedUser));
    }

    @Override
    public User getExistUser(String email) {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new ApiRequestException("User not found"));
    }

    private User getByUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new ApiRequestException("User with id: " + userId + " not found"));
    }

    private User getUpdatedUser(Long userId, UserRequestDTO userRequestDto) {
        User user = getByUserId(userId);
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        return user;
    }

}
