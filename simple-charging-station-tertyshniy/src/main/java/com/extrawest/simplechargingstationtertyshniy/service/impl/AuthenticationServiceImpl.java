package com.extrawest.simplechargingstationtertyshniy.service.impl;

import com.extrawest.simplechargingstationtertyshniy.exception.ApiRequestException;
import com.extrawest.simplechargingstationtertyshniy.model.Role;
import com.extrawest.simplechargingstationtertyshniy.model.User;
import com.extrawest.simplechargingstationtertyshniy.model.dto.request.UserRequestDTO;
import com.extrawest.simplechargingstationtertyshniy.model.dto.response.UserResponseDTO;
import com.extrawest.simplechargingstationtertyshniy.model.mapper.UserMapper;
import com.extrawest.simplechargingstationtertyshniy.repository.UserRepository;
import com.extrawest.simplechargingstationtertyshniy.security.jwt.JwtTokenProvider;
import com.extrawest.simplechargingstationtertyshniy.service.AuthenticationService;
import com.extrawest.simplechargingstationtertyshniy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserResponseDTO register(UserRequestDTO userRequestDto) {
        if (userRepository.findByEmail(userRequestDto.getEmail()).isPresent()) {
            throw new ApiRequestException("Bad credentials: " + userRequestDto.getEmail());
        }
        return create(userRequestDto);
    }

    @Override
    public String login(String login, String password) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        User user = userService.getExistUser(login);
//        String encodedPassword = passwordEncoder.passwordEncoder().encode(password);
//        if (user.getPassword().equals(encodedPassword)) {
//            throw new ApiRequestException(ExceptionMessage.BAD_CREDENTIALS);
//        }
        return jwtTokenProvider.createToken(login, user.getRole().name());
    }

    private UserResponseDTO create(UserRequestDTO userRequestDto) {
        User user = userMapper.toModel(userRequestDto);
        user.setRole(Role.BUYER);
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        return userMapper.toDto(userRepository.save(user));
    }
}
