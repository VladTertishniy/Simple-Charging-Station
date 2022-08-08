package com.extrawest.simplechargingstationtertyshniy.model.dto.response;

import com.extrawest.simplechargingstationtertyshniy.model.Location;
import lombok.Data;

@Data
public class ChargePointResponseDTO {
    private String chargePointModel;
    private String address;
    private String ownerName;
    private String ownerEmail;
}
