package com.extrawest.simplechargingstationtertyshniy.security;

import com.extrawest.simplechargingstationtertyshniy.exception.ApiRequestException;
import com.extrawest.simplechargingstationtertyshniy.model.User;
import com.extrawest.simplechargingstationtertyshniy.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@AllArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userService.getExistUser(email);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ApiRequestException("Bad credentials");
        }
        return new UsernamePasswordAuthenticationToken(email, password, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
