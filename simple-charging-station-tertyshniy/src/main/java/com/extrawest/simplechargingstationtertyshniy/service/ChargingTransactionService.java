package com.extrawest.simplechargingstationtertyshniy.service;

import com.extrawest.simplechargingstationtertyshniy.model.dto.request.ChargingTransactionRequestDTO;
import com.extrawest.simplechargingstationtertyshniy.model.dto.response.ChargingTransactionResponseDTO;
import com.extrawest.simplechargingstationtertyshniy.model.dto.response.DeleteResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChargingTransactionService {

    Page<ChargingTransactionResponseDTO> getAll(Pageable pageable);

    ChargingTransactionResponseDTO getById(Long chargingTransactionId);

    DeleteResponseDTO delete(Long chargingTransactionId);

    ChargingTransactionResponseDTO update(Long chargingTransactionId,
                                          ChargingTransactionRequestDTO chargingTransactionRequestDto);

    Long startTransaction(String email, ChargingTransactionRequestDTO chargingTransactionRequestDto);
}

