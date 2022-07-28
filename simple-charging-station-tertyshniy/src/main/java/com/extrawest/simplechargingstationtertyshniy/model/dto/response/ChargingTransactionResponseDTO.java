package com.extrawest.simplechargingstationtertyshniy.model.dto.response;

import lombok.Data;

@Data
public class ChargingTransactionResponseDTO {
    private long id;
    private ChargePointResponseDTO chargePointResponseDto;
    private UserResponseDTO user;
}

