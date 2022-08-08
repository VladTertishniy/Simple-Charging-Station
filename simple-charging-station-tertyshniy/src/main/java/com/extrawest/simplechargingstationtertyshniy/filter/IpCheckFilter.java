package com.extrawest.simplechargingstationtertyshniy.filter;

import com.extrawest.simplechargingstationtertyshniy.exception.ApiRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class IpCheckFilter implements Filter {
    private final Set<String> blackListIps = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        blackListIps.add("0:0:0:0:0:0:0:2");
        blackListIps.add("0:0:0:0:0:0:1:1");
        blackListIps.add("0:0:0:0:0:1:0:1");
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String ipAddress = request.getLocalAddr();
        if (blackListIps.contains(ipAddress)) {
            log.info("=> Your ip address is on black list {}", ipAddress);
            throw new ApiRequestException("Bad credentials");
        }
        chain.doFilter(request, response);
        log.info("=> Your ip address is clear {}", ipAddress);
    }
}
