package com.br.authentication_microservice.modules.user.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.br.authentication_microservice.modules.user.dto.UserDTO;
import com.br.authentication_microservice.modules.user.model.User;
import com.br.authentication_microservice.modules.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public List<UserDTO> getAll() {
    List<User> users = userRepository.findAll();
    List<UserDTO> list = users.stream().map(user -> new UserDTO(user.getId(), user.getEmail()))
        .collect(Collectors.toList());
    return list;
  }

  public UserDTO findById(UUID id) {
    User user = userRepository.findById(id).orElseThrow(() -> {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!");
    });
    return new UserDTO(user.getId(), user.getEmail());
  }
}
