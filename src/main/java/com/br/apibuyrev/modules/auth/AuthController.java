package com.br.apibuyrev.modules.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.apibuyrev.modules.auth.model.AuthenticationRequest;
import com.br.apibuyrev.modules.auth.model.AuthenticationResponse;
import com.br.apibuyrev.modules.auth.model.RegisterRequest;
import com.br.apibuyrev.modules.auth.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
  @Autowired
  private AuthService authService;

  @PostMapping("/register/client")
  public ResponseEntity<String> registerClient(@RequestBody @Valid RegisterRequest registerRequest) {
    authService.registerClient(registerRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso!");
  }

  @PostMapping("/register/company")
  public ResponseEntity<String> registerCompany(@RequestBody @Valid RegisterRequest registerRequest) {
    authService.registerCompany(registerRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso!");
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
    return ResponseEntity.ok().body(authService.authenticate(authenticationRequest));
  }
}
