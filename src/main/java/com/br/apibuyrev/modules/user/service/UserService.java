package com.br.apibuyrev.modules.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.br.apibuyrev.modules.user.dto.UserDTO;
import com.br.apibuyrev.modules.user.model.User;
import com.br.apibuyrev.modules.user.repository.UserRepository;

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
}
