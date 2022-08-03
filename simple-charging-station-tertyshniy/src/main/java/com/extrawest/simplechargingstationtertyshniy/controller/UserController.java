package com.extrawest.simplechargingstationtertyshniy.controller;

import com.extrawest.simplechargingstationtertyshniy.model.dto.request.UserRequestDTO;
import com.extrawest.simplechargingstationtertyshniy.model.dto.response.UserResponseDTO;
import com.extrawest.simplechargingstationtertyshniy.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> save(@RequestBody @Valid UserRequestDTO userRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userRequestDto));
    }

    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> getAll(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll(pageable));
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok("User with id: " + id + " deleted");
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id,
                                                  @RequestBody @Valid UserRequestDTO userRequestDto) {
        return ResponseEntity.ok(userService.update(id, userRequestDto));
    }
}

