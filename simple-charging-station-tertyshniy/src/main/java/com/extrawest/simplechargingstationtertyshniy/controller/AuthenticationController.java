package com.extrawest.simplechargingstationtertyshniy.controller;

import com.extrawest.simplechargingstationtertyshniy.model.dto.request.AuthenticationRequestDTO;
import com.extrawest.simplechargingstationtertyshniy.model.dto.request.UserRequestDTO;
import com.extrawest.simplechargingstationtertyshniy.model.dto.response.UserResponseDTO;
import com.extrawest.simplechargingstationtertyshniy.security.PrincipalUser;
import com.extrawest.simplechargingstationtertyshniy.security.SecurityUser;
import com.extrawest.simplechargingstationtertyshniy.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid UserRequestDTO userRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.register(userRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid AuthenticationRequestDTO requestDto) {
        String token = authenticationService.login(requestDto.getEmail(), requestDto.getPassword());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler logout = new SecurityContextLogoutHandler();
        logout.logout(request, response, null);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<String> getRefreshToken(@PrincipalUser SecurityUser securityUser) {
        String token = authenticationService.getRefreshToken(securityUser.getUsername(), securityUser.getPassword());
        return ResponseEntity.ok(token);
    }
}
