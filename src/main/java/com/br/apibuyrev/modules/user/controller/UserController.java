package com.br.apibuyrev.modules.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.apibuyrev.modules.user.dto.UserDTO;
import com.br.apibuyrev.modules.user.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping("")
  public ResponseEntity<List<UserDTO>> getAllUsers() {
    return ResponseEntity.ok().body(userService.getAll());
  }
}
