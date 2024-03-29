package com.epam.esm.controller.exception;

import com.epam.esm.service.api.exception.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ExceptionMessageTranslator translator;

    @Autowired
    public SecurityAuthenticationEntryPoint(ExceptionMessageTranslator translator) {
        this.translator = translator;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        ObjectMapper objectMapper = new ObjectMapper();
        String errorMessage = translator.translateToLocale(ErrorCode.FORBIDDEN_ACCESS);
        String jsonResponse = objectMapper.writeValueAsString(new ErrorResponse(errorMessage, ErrorCode.FORBIDDEN_ACCESS));
        response.getWriter().write(jsonResponse);
    }
}
