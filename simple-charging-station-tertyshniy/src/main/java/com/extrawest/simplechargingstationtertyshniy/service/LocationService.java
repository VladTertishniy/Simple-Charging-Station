package com.extrawest.simplechargingstationtertyshniy.service;

import com.extrawest.simplechargingstationtertyshniy.model.Location;
import com.extrawest.simplechargingstationtertyshniy.model.dto.request.LocationRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LocationService {
    Location create(LocationRequestDTO locationRequestDto);

    Page<Location> getAll(Pageable pageable);

    Location getById(Long id);

    void delete(Long id);

    Location update(Long locationId, LocationRequestDTO locationRequestDto);
}
