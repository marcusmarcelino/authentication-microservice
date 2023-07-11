package com.br.authentication_microservice.modules.auth.service;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.br.authentication_microservice.modules.auth.model.AuthenticationRequest;
import com.br.authentication_microservice.modules.auth.model.AuthenticationResponse;
import com.br.authentication_microservice.modules.auth.model.RegisterRequest;
import com.br.authentication_microservice.modules.user.enums.TypeOfRole;
import com.br.authentication_microservice.modules.user.model.Address;
import com.br.authentication_microservice.modules.user.model.Contact;
import com.br.authentication_microservice.modules.user.model.PersonalData;
import com.br.authentication_microservice.modules.user.model.User;
import com.br.authentication_microservice.modules.user.repository.UserRepository;
import com.br.authentication_microservice.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  public User registerClient(RegisterRequest registerRequest) {
    User user = prepareUserRegister(registerRequest);
    user.setRoles(Arrays.asList(TypeOfRole.CLIENT));
    return userRepository.save(user);
  }

  public User registerCompany(RegisterRequest registerRequest) {
    User user = prepareUserRegister(registerRequest);
    user.setRoles(Arrays.asList(TypeOfRole.COMPANY));
    return userRepository.save(user);
  }

  private User prepareUserRegister(RegisterRequest registerRequest) {
    userAlreadyExist(registerRequest);

    Contact contact = Contact.builder()
        .withContact(registerRequest.getContact())
        .withType(registerRequest.getTypeOfContact())
        .build();

    Address address = Address.builder()
        .withDistrict(registerRequest.getDistrict())
        .withRoad(registerRequest.getRoad())
        .withNumber(registerRequest.getNumber())
        .withComplement(registerRequest.getComplement())
        .withCity(registerRequest.getCity())
        .withUf(registerRequest.getUf())
        .build();

    PersonalData personaldata = PersonalData
        .builder()
        .withFirstname(registerRequest.getFirstname())
        .withLastname(registerRequest.getLastname())
        .withTypeOfperson(registerRequest.getTypeOfPerson())
        .withContacts(Arrays.asList(contact))
        .withAddress(Arrays.asList(address))
        .build();

    personaldata.handleIdentificationByTypeOfPerson(registerRequest);

    User user = User.builder()
        .withPersonalData(personaldata)
        .withEmail(registerRequest.getEmail())
        .withPassword(passwordEncoder.encode(registerRequest.getPassword()))
        .build();

    personaldata.setUser(user);

    return user;
  }

  private void userAlreadyExist(RegisterRequest registerRequest) {
    Optional<User> existingUser = userRepository.findByEmail(registerRequest.getEmail());
    if (existingUser.isPresent())
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Usuário já cadastrado.");
  }

  public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

    User user = userRepository.findByEmail(authRequest.getEmail())
        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
    var token = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .withToken(token)
        .withAuthenticatedUser(user.getPersonalData().getFirstname() + " " + user.getPersonalData().getLastname())
        .build();
  }
}
