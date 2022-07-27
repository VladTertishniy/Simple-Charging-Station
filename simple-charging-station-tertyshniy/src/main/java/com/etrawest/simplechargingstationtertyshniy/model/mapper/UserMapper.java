package com.etrawest.simplechargingstationtertyshniy.model.mapper;

import com.etrawest.simplechargingstationtertyshniy.model.User;
import com.etrawest.simplechargingstationtertyshniy.model.dto.request.UserRequestDTO;
import com.etrawest.simplechargingstationtertyshniy.model.dto.response.UserResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserRequestDTO toUserRequestDTO(User user);
    UserResponseDTO toUserResponseDTO(User user);
    User toUserModel(UserRequestDTO userRequestDTO);
    User toUserModel(UserResponseDTO userResponseDTO);
}
