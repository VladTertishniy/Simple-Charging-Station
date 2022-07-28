package com.extrawest.simplechargingstationtertyshniy.controller;

import com.extrawest.simplechargingstationtertyshniy.model.dto.request.ChargePointRequestDTO;
import com.extrawest.simplechargingstationtertyshniy.model.dto.response.ChargePointResponseDTO;
import com.extrawest.simplechargingstationtertyshniy.service.ChargePointService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/chargePoints")
@AllArgsConstructor
public class ChargePointController {
    private final ChargePointService chargePointService;

    @PostMapping
    public ResponseEntity<ChargePointResponseDTO> save(
            @RequestBody @Valid ChargePointRequestDTO chargePointRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(chargePointService.create(chargePointRequestDto));
    }

    @GetMapping
    public ResponseEntity<Page<ChargePointResponseDTO>> getAll(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(chargePointService.getAll(pageable));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ChargePointResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(chargePointService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        chargePointService.delete(id);
        return ResponseEntity.ok("Charge point with id: " + id + " deleted");
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<ChargePointResponseDTO> deleteById(
            @PathVariable Long id,
            @RequestBody @Valid ChargePointRequestDTO chargePointRequestDto) {
        chargePointService.delete(id);
        return ResponseEntity.ok(chargePointService.update(id, chargePointRequestDto));
    }
}
