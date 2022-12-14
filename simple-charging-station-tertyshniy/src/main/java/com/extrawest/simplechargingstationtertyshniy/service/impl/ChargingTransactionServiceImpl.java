package com.extrawest.simplechargingstationtertyshniy.service.impl;

import com.extrawest.simplechargingstationtertyshniy.exception.ApiRequestException;
import com.extrawest.simplechargingstationtertyshniy.model.ChargingTransaction;
import com.extrawest.simplechargingstationtertyshniy.model.dto.request.ChargingTransactionRequestDTO;
import com.extrawest.simplechargingstationtertyshniy.model.dto.response.ChargingTransactionResponseDTO;
import com.extrawest.simplechargingstationtertyshniy.model.dto.response.DeleteResponseDTO;
import com.extrawest.simplechargingstationtertyshniy.model.mapper.ChargingTransactionMapper;
import com.extrawest.simplechargingstationtertyshniy.repository.ChargingTransactionRepository;
import com.extrawest.simplechargingstationtertyshniy.service.ChargePointService;
import com.extrawest.simplechargingstationtertyshniy.service.ChargingTransactionService;
import com.extrawest.simplechargingstationtertyshniy.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChargingTransactionServiceImpl implements ChargingTransactionService {
    private final ChargingTransactionRepository chargingTransactionRepository;
    private final ChargingTransactionMapper chargingTransactionMapper;
    private final ChargePointService chargePointService;
    private final UserService userService;

    @Override
    public Page<ChargingTransactionResponseDTO> getAll(Pageable pageable) {
        Page<ChargingTransactionResponseDTO> all = chargingTransactionRepository
                .findAll(pageable).map(chargingTransactionMapper::toDto);
        return new PageImpl<>(all.getContent(), pageable, all.getTotalElements());
    }

    @Override
    public ChargingTransactionResponseDTO getById(Long chargingTransactionId) {
        return chargingTransactionMapper.toDto(getChargingTransactionById(chargingTransactionId));
    }

    public DeleteResponseDTO delete(Long chargingTransactionId) {
        chargingTransactionRepository.delete(getChargingTransactionById(chargingTransactionId));
        return new DeleteResponseDTO("Charging transaction deleted, id: ", chargingTransactionId);
    }

    @Override
    public ChargingTransactionResponseDTO update(Long chargingTransactionId,
                                                 ChargingTransactionRequestDTO chargingTransactionRequestDto) {
        ChargingTransaction chargingTransaction = getChargingTransactionById(chargingTransactionId);
        chargingTransaction.setChargePoint(chargePointService
                .getChargePointById(chargingTransactionRequestDto.getChargePointId()));
        return chargingTransactionMapper.toDto(chargingTransactionRepository.save(chargingTransaction));
    }

    @Override
    public Long startTransaction(String email, ChargingTransactionRequestDTO chargingTransactionRequestDto) {
        if (chargePointService.getById(chargingTransactionRequestDto.getChargePointId()) != null) {
            ChargingTransaction chargingTransaction = chargingTransactionMapper.toModel(chargingTransactionRequestDto);
            chargingTransaction.setUser(userService.getExistUser(email));
            return chargingTransactionRepository.save(chargingTransaction).getId();
        }
        throw new ApiRequestException("No charging transaction");
    }

    private ChargingTransaction getChargingTransactionById(Long chargingTransactionId) {
        return chargingTransactionRepository.findById(chargingTransactionId).orElseThrow(() ->
                new ApiRequestException("Charging transaction with id: " + chargingTransactionId + " not found"));
    }

}
