package com.br.authentication_microservice.security;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.br.authentication_microservice.error.ErrorObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomUnauthorizedHandler implements AuthenticationEntryPoint {

  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException)
      throws IOException, ServletException {
    String msg = "Acesso não autorizado. ";
    if (response.getHeader("token_access_error") != null) {
      msg += response.getHeader("token_access_error");
      response.reset();
    }

    ErrorObject errorResponse = ErrorObject.builder()
        .status("UNAUTHORIZED")
        .message(msg)
        .code(HttpStatus.UNAUTHORIZED.value())
        .build();

    response.setCharacterEncoding("UTF-8");
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
  }
}
