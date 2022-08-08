package com.extrawest.simplechargingstationtertyshniy.controller;

import com.extrawest.simplechargingstationtertyshniy.model.dto.request.ChargePointRequestDTO;
import com.extrawest.simplechargingstationtertyshniy.model.dto.response.ChargePointResponseDTO;
import com.extrawest.simplechargingstationtertyshniy.model.dto.response.DeleteResponseDTO;
import com.extrawest.simplechargingstationtertyshniy.security.PrincipalUser;
import com.extrawest.simplechargingstationtertyshniy.security.SecurityUser;
import com.extrawest.simplechargingstationtertyshniy.service.ChargePointService;
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
@RequestMapping("/chargePoints")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class ChargePointController {
    private final ChargePointService chargePointService;

    @PostMapping
    @PreAuthorize("hasAuthority('users:create')")
    public ResponseEntity<ChargePointResponseDTO> save(
            @PrincipalUser SecurityUser securityUser,
            @RequestBody @Valid ChargePointRequestDTO chargePointRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(chargePointService.create(securityUser.getUsername(), chargePointRequestDto));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('users:read', 'users:update')")
    public ResponseEntity<Page<ChargePointResponseDTO>> getAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(chargePointService.getAll(pageable));
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAnyAuthority('users:read', 'users:update')")
    public ResponseEntity<ChargePointResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(chargePointService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('users:remove', 'users:create')")
    public ResponseEntity<DeleteResponseDTO> deleteById(@PathVariable Long id, @PrincipalUser SecurityUser securityUser) {
        return ResponseEntity.ok(chargePointService.delete(securityUser.getUsername(), id));
    }

    @PutMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('users:update')")
    public ResponseEntity<ChargePointResponseDTO> update(
            @PrincipalUser SecurityUser securityUser,
            @PathVariable Long id,
            @RequestBody @Valid ChargePointRequestDTO chargePointRequestDto) {
        return ResponseEntity.ok(chargePointService.update(securityUser.getUsername(), id, chargePointRequestDto));
    }
}
