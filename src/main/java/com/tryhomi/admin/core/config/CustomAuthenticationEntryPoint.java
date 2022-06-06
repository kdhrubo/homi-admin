package com.tryhomi.admin.core.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CustomAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    public CustomAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        if (HtmxDetector.isHtmx(request)) {
            log.info("Handling HTMX logout");
            response.setHeader("session_timeout", "true");
            response.setHeader("HX-Redirect",getLoginFormUrl());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            log.info("Handling Non-HTMX logout");
            super.commence(request, response, authException);
        }
    }
}
