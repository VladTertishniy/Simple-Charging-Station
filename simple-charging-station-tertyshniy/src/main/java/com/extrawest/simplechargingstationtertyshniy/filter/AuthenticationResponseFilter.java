package com.extrawest.simplechargingstationtertyshniy.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class AuthenticationResponseFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        int authentication = response.getStatus();
        if (authentication != 200) {
            if (authentication == 401 || authentication == 403) {
                log.info("=> You have no rights for watching this content {}", authentication);
                filterChain.doFilter(request, response);
            }
            log.info("=> Page not found {}", authentication);
            filterChain.doFilter(request, response);
        }
        log.info("=> Congratulation with authentication {}", authentication);
        filterChain.doFilter(request, response);
    }
}
