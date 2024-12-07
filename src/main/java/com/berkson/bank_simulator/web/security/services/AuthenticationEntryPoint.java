package com.berkson.bank_simulator.web.security.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

/**
 * created by Berkson Ximenes
 * Date: 07/12/2024
 */
@Slf4j
@Component
public class AuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    public void afterPropertiesSet() {
        setRealmName("bank_simulator");
        super.afterPropertiesSet();
    }

    public AuthenticationEntryPoint(HandlerExceptionResolver handlerExceptionResolver) {
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    // @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) {
        log.info("Ponto de autenticação!");
        handlerExceptionResolver.resolveException(request, response, null, authException);
    }
}
