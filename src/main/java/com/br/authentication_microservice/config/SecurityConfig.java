package com.br.authentication_microservice.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.br.authentication_microservice.error.ErrorObject;
import com.br.authentication_microservice.security.AuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
  private final AuthenticationProvider authProvider;
  private final AuthenticationFilter authFilter;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests((authz) -> authz
            .requestMatchers("/api/v1/auth/**").permitAll()
            .requestMatchers("/api/v1/users/**").hasAnyRole("ADMIN")
            .anyRequest().authenticated())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authenticationProvider(authProvider)
        .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling(ex -> ex.authenticationEntryPoint(new UnauthorizedHandler()));

    return http.build();
  }

  private class UnauthorizedHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(
        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException authException) throws IOException, ServletException {
      // response.reset();
      // response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
      // response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      // response.setContentType("application/json");
      // response.reset(); // Limpa qualquer configuração anterior da resposta

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
      response.setContentType("application/json");
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
    }
  }
}
