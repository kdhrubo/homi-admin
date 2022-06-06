package com.tryhomi.admin.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.ExceptionTranslationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CustomExceptionTranslationFilter extends ExceptionTranslationFilter {

    private final AuthenticationEntryPoint authenticationEntryPoint;

    public CustomExceptionTranslationFilter(AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationEntryPoint);
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    protected void sendStartAuthentication(final HttpServletRequest request,
                                           final HttpServletResponse response,
                                           final FilterChain chain,
                                           final AuthenticationException authenticationException) throws ServletException, IOException {

        if (HtmxDetector.isHtmx(request)) {
            SecurityContextHolder.getContext().setAuthentication(null);
            authenticationEntryPoint.commence(request, response, authenticationException);
        } else {
            super.sendStartAuthentication(new CustomHttpServletRequestWrapper(request), response, chain, authenticationException);
        }
    }


    private final static class CustomHttpServletRequestWrapper extends HttpServletRequestWrapper {
        private final HttpServletRequest httpServletRequest;

        public CustomHttpServletRequestWrapper(HttpServletRequest request) {
            super(request);
            this.httpServletRequest = request;
        }
    }
}
