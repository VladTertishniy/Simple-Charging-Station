package com.extrawest.simplechargingstationtertyshniy.service;

import com.extrawest.simplechargingstationtertyshniy.model.dto.request.ChargePointRequestDTO;
import com.extrawest.simplechargingstationtertyshniy.model.dto.response.ChargePointResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChargePointService {
    ChargePointResponseDTO create(ChargePointRequestDTO chargePointRequestDto);

    Page<ChargePointResponseDTO> getAll(Pageable pageable);

    ChargePointResponseDTO getById(Long id);

    void delete(Long id);

    ChargePointResponseDTO update(Long chargePointId, ChargePointRequestDTO chargePointRequestDto);
}

