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
    @Mapping(source = "location.address", target = "address")
    @Mapping(source = "user.name", target = "ownerName")
    @Mapping(source = "user.email", target = "ownerEmail")
    ChargePointResponseDTO toDto(ChargePoint chargePoint);

    @Mapping(source = "locationId", target = "location.id")
    ChargePoint toModel(ChargePointRequestDTO chargePointRequestDto);
}

