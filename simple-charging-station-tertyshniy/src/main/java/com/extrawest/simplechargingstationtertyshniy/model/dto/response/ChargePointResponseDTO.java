package com.extrawest.simplechargingstationtertyshniy.model.dto.response;

import com.extrawest.simplechargingstationtertyshniy.model.Location;
import lombok.Data;

@Data
public class ChargePointResponseDTO {
    private String chargePointModel;
    private Location location;
    private UserResponseDTO user;
}
