package com.extrawest.simplechargingstationtertyshniy.service.impl;

import com.extrawest.simplechargingstationtertyshniy.exception.ApiRequestException;
import com.extrawest.simplechargingstationtertyshniy.model.Location;
import com.extrawest.simplechargingstationtertyshniy.model.dto.request.LocationRequestDTO;
import com.extrawest.simplechargingstationtertyshniy.model.dto.response.DeleteResponseDTO;
import com.extrawest.simplechargingstationtertyshniy.model.mapper.LocationMapper;
import com.extrawest.simplechargingstationtertyshniy.repository.LocationRepository;
import com.extrawest.simplechargingstationtertyshniy.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    @Override
    public Location create(LocationRequestDTO locationRequestDto) {
        return locationRepository.save(locationMapper.toModel(locationRequestDto));
    }

    @Override
    public Page<Location> getAll(Pageable pageable) {
        Page<Location> all = locationRepository.findAll(pageable);
        return new PageImpl<>(all.getContent(), pageable, all.getTotalElements());
    }

    @Override
    public Location getById(Long locationId) {
        return locationRepository.findById(locationId).orElseThrow(() ->
                new ApiRequestException("Location with id: " + locationId + " not found"));
    }

    @Override
    public DeleteResponseDTO delete(Long locationId) {
        locationRepository.delete(getById(locationId));
        return new DeleteResponseDTO("Location deleted, id: ", locationId);
    }

    @Override
    public Location update(Long locationId, LocationRequestDTO locationRequestDto) {
        Location location = getById(locationId);
        location.setAddress(locationRequestDto.getAddress());
        return locationRepository.save(location);
    }

}
