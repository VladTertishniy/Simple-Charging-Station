package com.extrawest.simplechargingstationtertyshniy.model.mapper;

import com.extrawest.simplechargingstationtertyshniy.model.ChargingTransaction;
import com.extrawest.simplechargingstationtertyshniy.model.dto.request.ChargingTransactionRequestDTO;
import com.extrawest.simplechargingstationtertyshniy.model.dto.response.ChargingTransactionResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChargingTransactionMapper {

    @Mapping(source = "chargePointId", target = "chargePoint.id")
    ChargingTransaction toModel(ChargingTransactionRequestDTO chargingTransactionRequestDto);

    ChargingTransactionResponseDTO toDto(ChargingTransaction chargingTransaction);
}

