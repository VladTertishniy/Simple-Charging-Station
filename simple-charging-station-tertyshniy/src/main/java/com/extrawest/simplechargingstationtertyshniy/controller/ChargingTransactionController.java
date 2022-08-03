package com.extrawest.simplechargingstationtertyshniy.controller;

import com.extrawest.simplechargingstationtertyshniy.model.dto.request.ChargingTransactionRequestDTO;
import com.extrawest.simplechargingstationtertyshniy.model.dto.response.ChargingTransactionResponseDTO;
import com.extrawest.simplechargingstationtertyshniy.service.ChargingTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/chargingTransactions")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class ChargingTransactionController {
    private final ChargingTransactionService chargingTransactionService;


    @GetMapping
    public ResponseEntity<Page<ChargingTransactionResponseDTO>> getAll(
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(chargingTransactionService.getAll(pageable));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ChargingTransactionResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(chargingTransactionService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        chargingTransactionService.delete(id);
        return ResponseEntity.ok("Transaction with id: " + id + " deleted");
    }

    @PostMapping("/start")
    public ResponseEntity<String> startTransaction(
            @RequestBody @Valid ChargingTransactionRequestDTO chargingTransactionRequestDto) {
        return ResponseEntity.ok("Transaction "
                + chargingTransactionService.startTransaction(chargingTransactionRequestDto)
                + "started successfully!");
    }
}

