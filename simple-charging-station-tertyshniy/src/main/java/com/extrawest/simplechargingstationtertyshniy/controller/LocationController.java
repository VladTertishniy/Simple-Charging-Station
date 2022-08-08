package com.extrawest.simplechargingstationtertyshniy.controller;

import com.extrawest.simplechargingstationtertyshniy.model.Location;
import com.extrawest.simplechargingstationtertyshniy.model.dto.request.LocationRequestDTO;
import com.extrawest.simplechargingstationtertyshniy.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/locations")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class LocationController {
    private final LocationService locationService;

    @PostMapping
    @PreAuthorize("hasAuthority('users:update')")
    public ResponseEntity<Location> save(@RequestBody LocationRequestDTO locationRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(locationService.create(locationRequestDto));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('users:read')")
    public ResponseEntity<Page<Location>> getAll(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(locationService.getAll(pageable));
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('users:update')")
    public ResponseEntity<Location> getById(@PathVariable Long id) {
        return ResponseEntity.ok(locationService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('users:update')")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        locationService.delete(id);
        return ResponseEntity.ok("Location with id: " + id + " deleted");
    }

    @PutMapping("delete/{id}")
    @PreAuthorize("hasAnyAuthority('users:update', 'users:remove')")
    public ResponseEntity<Location> deleteById(@PathVariable Long id,
                                               @RequestBody @Valid LocationRequestDTO locationRequestDto) {
        locationService.delete(id);
        return ResponseEntity.ok(locationService.update(id, locationRequestDto));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('users:update')")
    public ResponseEntity<Location> update(@PathVariable Long id,
                                           @RequestBody LocationRequestDTO locationRequestDto) {
        return ResponseEntity.ok(locationService.update(id, locationRequestDto));
    }
}

