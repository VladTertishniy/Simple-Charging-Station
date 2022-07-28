package com.extrawest.simplechargingstationtertyshniy.service.impl;

import com.extrawest.simplechargingstationtertyshniy.exception.ApiRequestException;
import com.extrawest.simplechargingstationtertyshniy.model.ChargePoint;
import com.extrawest.simplechargingstationtertyshniy.model.dto.request.ChargePointRequestDTO;
import com.extrawest.simplechargingstationtertyshniy.model.dto.response.ChargePointResponseDTO;
import com.extrawest.simplechargingstationtertyshniy.model.mapper.ChargePointMapper;
import com.extrawest.simplechargingstationtertyshniy.repository.ChargePointRepository;
import com.extrawest.simplechargingstationtertyshniy.service.ChargePointService;
import com.extrawest.simplechargingstationtertyshniy.service.LocationService;
import com.extrawest.simplechargingstationtertyshniy.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChargePointServiceImpl implements ChargePointService {
    private final ChargePointRepository chargePointRepository;
    private final ChargePointMapper chargePointMapper;
    private final LocationService locationService;
    private final UserService userService;

    @Override
    public ChargePointResponseDTO create(ChargePointRequestDTO chargePointRequestDto) {
        if (locationService.getById(chargePointRequestDto.getLocationId()) != null
                || userService.getById(chargePointRequestDto.getUserId()) != null) {
            return chargePointMapper.toDto(chargePointRepository
                    .save(chargePointMapper.toModel(chargePointRequestDto)));
        }
        throw new ApiRequestException("No charge point");
    }

    @Override
    public Page<ChargePointResponseDTO> getAll(Pageable pageable) {
        Page<ChargePointResponseDTO> all = chargePointRepository.findAll(pageable).map(chargePointMapper::toDto);
        return new PageImpl<>(all.getContent(), pageable, all.getTotalElements());
    }

    @Override
    public ChargePointResponseDTO getById(Long chargePointId) {
        return chargePointMapper.toDto(getChargePointById(chargePointId));
    }

    @Override
    public void delete(Long chargePointId) {
        chargePointRepository.delete(getChargePointById(chargePointId));
    }

    @Override
    public ChargePointResponseDTO update(Long chargePointId, ChargePointRequestDTO chargePointRequestDto) {
        getById(chargePointId);
        ChargePoint chargePoint = chargePointMapper.toModel(chargePointRequestDto);
        chargePoint.setId(chargePointId);
        return chargePointMapper.toDto(chargePointRepository.save(chargePoint));
    }

    private ChargePoint getChargePointById(Long chargePointId) {
        return chargePointRepository.findById(chargePointId).orElseThrow(() ->
                new ApiRequestException("Charge point with id: " + chargePointId + " not found"));
    }
}
