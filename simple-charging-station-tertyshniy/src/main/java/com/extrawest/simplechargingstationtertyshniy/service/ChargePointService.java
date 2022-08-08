package com.extrawest.simplechargingstationtertyshniy.service;

import com.extrawest.simplechargingstationtertyshniy.model.dto.request.ChargePointRequestDTO;
import com.extrawest.simplechargingstationtertyshniy.model.dto.response.ChargePointResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChargePointService {
    ChargePointResponseDTO create(String email, ChargePointRequestDTO chargePointRequestDto);

    Page<ChargePointResponseDTO> getAll(Pageable pageable);

    ChargePointResponseDTO getById(Long id);

    void delete(String email, Long id);

    ChargePointResponseDTO update(String email, Long chargePointId, ChargePointRequestDTO chargePointRequestDto);
}

