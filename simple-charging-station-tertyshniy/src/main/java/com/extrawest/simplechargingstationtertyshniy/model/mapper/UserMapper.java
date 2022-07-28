package com.extrawest.simplechargingstationtertyshniy.model.mapper;

import com.extrawest.simplechargingstationtertyshniy.model.User;
import com.extrawest.simplechargingstationtertyshniy.model.dto.request.UserRequestDTO;
import com.extrawest.simplechargingstationtertyshniy.model.dto.response.UserResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDTO toDto(User user);
    User toModel(UserRequestDTO userRequestDto);
    UserRequestDTO toUserRequestDTO(User user);
    UserResponseDTO toUserResponseDTO(User user);
    User toUserModel(UserRequestDTO userRequestDTO);
    User toUserModel(UserResponseDTO userResponseDTO);
}
