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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public UserResponseDTO create(UserRequestDTO userRequestDto) {
        User user = userMapper.toModel(userRequestDto);
        user.setRole(Role.BUYER);
        return userMapper.toDto(userRepository.save(user));
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
    public void delete(Long userId) {
        userRepository.delete(getByUserId(userId));
    }

    @Override
    public UserResponseDTO update(Long userId, UserRequestDTO userRequestDto) {
        getById(userId);
        User user = userMapper.toModel(userRequestDto);
        user.setId(userId);
        return userMapper.toDto(userRepository.save(user));
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

}
