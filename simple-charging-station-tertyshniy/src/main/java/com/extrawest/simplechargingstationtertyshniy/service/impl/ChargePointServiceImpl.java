package com.extrawest.simplechargingstationtertyshniy.service.impl;

import com.extrawest.simplechargingstationtertyshniy.exception.ApiRequestException;
import com.extrawest.simplechargingstationtertyshniy.model.ChargePoint;
import com.extrawest.simplechargingstationtertyshniy.model.Location;
import com.extrawest.simplechargingstationtertyshniy.model.Role;
import com.extrawest.simplechargingstationtertyshniy.model.User;
import com.extrawest.simplechargingstationtertyshniy.model.dto.request.ChargePointRequestDTO;
import com.extrawest.simplechargingstationtertyshniy.model.dto.response.ChargePointResponseDTO;
import com.extrawest.simplechargingstationtertyshniy.model.dto.response.DeleteResponseDTO;
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

import java.util.List;

@Service
@AllArgsConstructor
public class ChargePointServiceImpl implements ChargePointService {
    private final ChargePointRepository chargePointRepository;
    private final ChargePointMapper chargePointMapper;
    private final LocationService locationService;
    private final UserService userService;

    @Override
    public ChargePointResponseDTO create(String email, ChargePointRequestDTO chargePointRequestDto) {
        Location location = locationService.getById(chargePointRequestDto.getLocationId());
        ChargePoint chargePoint = chargePointMapper.toModel(chargePointRequestDto);
        chargePoint.setUser(userService.getExistUser(email));
        chargePoint.setLocation(location);
        return chargePointMapper.toDto(chargePointRepository.save(chargePoint));
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
    public DeleteResponseDTO delete(String email, Long chargePointId) {
        User user = userService.getExistUser(email);
        if (Role.SELLER == user.getRole()) {
            findByUserAndId(user, chargePointId);
        }
        chargePointRepository.delete(getChargePointById(chargePointId));
        return new DeleteResponseDTO("Charge point deleted, id: ", chargePointId);
    }

    @Override
    public ChargePointResponseDTO update(String email,
                                         Long chargePointId,
                                         ChargePointRequestDTO chargePointRequestDto) {
        User user = userService.getExistUser(email);
        if (Role.SELLER == user.getRole()) {
            findByUserAndId(user, chargePointId);
        }
        Location location = locationService.getById(chargePointRequestDto.getLocationId());
        getById(chargePointId);
        ChargePoint chargePoint = chargePointMapper.toModel(chargePointRequestDto);
        chargePoint.setId(chargePointId);
        chargePoint.setLocation(location);
        chargePoint.setUser(chargePointRepository.findById(chargePointId).orElseThrow(() ->
                new ApiRequestException("No charge point with id: " + chargePointId)).getUser());
        return chargePointMapper.toDto(chargePointRepository.save(chargePoint));
    }

    @Override
    public ChargePoint findByUserAndId(User user, Long id) {
        return chargePointRepository.findByUserAndId(user, id)
                .orElseThrow(() -> new ApiRequestException("No rights"));
    }

    @Override
    public ChargePoint getChargePointById(long chargePointId) {
        return chargePointRepository.findById(chargePointId).orElseThrow(() ->
                new ApiRequestException("No charge points with id: " + chargePointId));
    }
}
