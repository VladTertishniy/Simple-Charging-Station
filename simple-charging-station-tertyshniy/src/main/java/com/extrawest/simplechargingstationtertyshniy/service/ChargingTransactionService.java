package com.extrawest.simplechargingstationtertyshniy.service;

import com.extrawest.simplechargingstationtertyshniy.model.dto.request.ChargingTransactionRequestDTO;
import com.extrawest.simplechargingstationtertyshniy.model.dto.response.ChargingTransactionResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChargingTransactionService {

    Page<ChargingTransactionResponseDTO> getAll(Pageable pageable);

    ChargingTransactionResponseDTO getById(Long chargingTransactionId);

    void delete(Long chargingTransactionId);

    ChargingTransactionResponseDTO update(Long chargingTransactionId,
                                          ChargingTransactionRequestDTO chargingTransactionRequestDto);

    Long startTransaction(ChargingTransactionRequestDTO chargingTransactionRequestDto);
}

