package com.extrawest.simplechargingstationtertyshniy.model.mapper;

import com.extrawest.simplechargingstationtertyshniy.model.ChargePoint;
import com.extrawest.simplechargingstationtertyshniy.model.dto.request.ChargePointRequestDTO;
import com.extrawest.simplechargingstationtertyshniy.model.dto.response.ChargePointResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChargePointMapper {

    //    @Mapping(target = "location", expression = "java(locationService.getById(chargePointRequestDto.getLocationId()))")
//    @Mapping(target = "user", expression = "java(userService.getById(chargePoint.getUserId()))")
//    @Mapping(source = "user.id", target = "location.id")
//    @Mapping(source = "userId", target = "user.id")
    ChargePointResponseDTO toDto(ChargePoint chargePoint);

    @Mapping(source = "locationId", target = "location.id")
    ChargePoint toModel(ChargePointRequestDTO chargePointRequestDto);
}

