package com.extrawest.simplechargingstationtertyshniy.service;

import com.extrawest.simplechargingstationtertyshniy.model.ChargePoint;
import com.extrawest.simplechargingstationtertyshniy.model.User;
import com.extrawest.simplechargingstationtertyshniy.model.dto.request.ChargePointRequestDTO;
import com.extrawest.simplechargingstationtertyshniy.model.dto.response.ChargePointResponseDTO;
import com.extrawest.simplechargingstationtertyshniy.model.dto.response.DeleteResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChargePointService {
    ChargePointResponseDTO create(String email, ChargePointRequestDTO chargePointRequestDto);

    Page<ChargePointResponseDTO> getAll(Pageable pageable);

    ChargePointResponseDTO getById(Long id);

    DeleteResponseDTO delete(String email, Long id);

    ChargePointResponseDTO update(String email, Long chargePointId, ChargePointRequestDTO chargePointRequestDto);

    ChargePoint getChargePointById(long chargePointId);
    ChargePoint findByUserAndId(User user, Long id);
}
