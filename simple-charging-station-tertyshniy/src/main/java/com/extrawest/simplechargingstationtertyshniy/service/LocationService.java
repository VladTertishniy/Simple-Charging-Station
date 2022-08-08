package com.extrawest.simplechargingstationtertyshniy.service;

import com.extrawest.simplechargingstationtertyshniy.model.Location;
import com.extrawest.simplechargingstationtertyshniy.model.dto.request.LocationRequestDTO;
import com.extrawest.simplechargingstationtertyshniy.model.dto.response.DeleteResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LocationService {
    Location create(LocationRequestDTO locationRequestDto);

    Page<Location> getAll(Pageable pageable);

    Location getById(Long id);

    DeleteResponseDTO delete(Long id);

    Location update(Long locationId, LocationRequestDTO locationRequestDto);
}
