package com.extrawest.simplechargingstationtertyshniy.model.mapper;

import com.extrawest.simplechargingstationtertyshniy.model.Location;
import com.extrawest.simplechargingstationtertyshniy.model.dto.request.LocationRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    Location toModel(LocationRequestDTO locationRequestDto);
}
