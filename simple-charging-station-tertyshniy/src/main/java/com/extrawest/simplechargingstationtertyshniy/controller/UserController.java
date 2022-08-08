package com.extrawest.simplechargingstationtertyshniy.controller;

import com.extrawest.simplechargingstationtertyshniy.model.dto.request.UserRequestDTO;
import com.extrawest.simplechargingstationtertyshniy.model.dto.response.UserResponseDTO;
import com.extrawest.simplechargingstationtertyshniy.security.PrincipalUser;
import com.extrawest.simplechargingstationtertyshniy.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.file.attribute.UserPrincipal;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('users:read', 'users:update')")
    public ResponseEntity<Page<UserResponseDTO>> getAll(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll(pageable));
    }

    @GetMapping("/getUser/{id}")
    @PreAuthorize("hasAnyAuthority('users:read', 'users:update')")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @DeleteMapping("/deleteUser/{id}")
    @PreAuthorize("hasAnyAuthority('users:read', 'users:create')")
    public ResponseEntity<String> deleteById(@PrincipalUser UserPrincipal userPrincipal, @PathVariable Long id) {
        userService.delete(userPrincipal.getName(), id);
        return ResponseEntity.ok("User with id: " + id + " deleted");
    }

    @PutMapping("/updateUser/{id}")
    @PreAuthorize("hasAnyAuthority('users:read', 'users:update')")
    public ResponseEntity<UserResponseDTO> update(@PrincipalUser UserPrincipal userPrincipal,
                                                  @PathVariable Long id,
                                                  @RequestBody @Valid UserRequestDTO userRequestDto) {
        return ResponseEntity.ok(userService.update(userPrincipal.getName(), id, userRequestDto));
    }
}
