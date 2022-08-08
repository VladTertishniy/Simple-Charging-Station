package com.extrawest.simplechargingstationtertyshniy.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class AuthenticationProcessFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (!request.getServletPath().equals("/login")) {
            filterChain.doFilter(request, response);
        } else {
            log.info("=> Authentication is on processing... {}", request.getUserPrincipal());
            filterChain.doFilter(request, response);
        }
    }
}
