package com.br.authentication_microservice.modules.auth.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AuthenticationRequest {
  @NotBlank(message = "{form.field.message}")
  private String email;
  @NotBlank(message = "{form.field.message}")
  private String password;
}
