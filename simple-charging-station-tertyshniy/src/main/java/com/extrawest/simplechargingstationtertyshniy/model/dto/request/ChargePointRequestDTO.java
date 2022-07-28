package com.extrawest.simplechargingstationtertyshniy.model.dto.request;

import lombok.Data;

@Data
public class ChargePointRequestDTO {
    private String chargePointModel;
    private long locationId;
    private long userId;
}

