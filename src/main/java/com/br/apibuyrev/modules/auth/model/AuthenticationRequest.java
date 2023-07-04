package com.br.apibuyrev.modules.auth.model;

import lombok.Getter;

@Getter
public class AuthenticationRequest {
  private String email;
  private String password;
}
