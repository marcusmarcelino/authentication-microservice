package com.br.authentication_microservice.modules.auth.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "with")
public class AuthenticationResponse {
  private String authenticatedUser;
  private String token;
}
