package com.br.apibuyrev.modules.auth.service;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.apibuyrev.modules.auth.model.AuthenticationRequest;
import com.br.apibuyrev.modules.auth.model.AuthenticationResponse;
import com.br.apibuyrev.modules.auth.model.RegisterRequest;
import com.br.apibuyrev.modules.user.enums.TypeOfRole;
import com.br.apibuyrev.modules.user.model.Address;
import com.br.apibuyrev.modules.user.model.Contact;
import com.br.apibuyrev.modules.user.model.PersonalData;
import com.br.apibuyrev.modules.user.model.User;
import com.br.apibuyrev.modules.user.repository.UserRepository;
import com.br.apibuyrev.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  private User prepareUserRegister(RegisterRequest registerRequest) {
    Optional<User> existingUser = userRepository.findByEmail(registerRequest.getEmail());
    if (existingUser.isPresent())
      return null;

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

    personaldata.setByTypeOfPerson(registerRequest);

    User user = User.builder()
        .withPersonalData(personaldata)
        .withEmail(registerRequest.getEmail())
        .withPassword(passwordEncoder.encode(registerRequest.getPassword()))
        .build();

    personaldata.setUser(user);

    return user;
  }

  public User registerClient(RegisterRequest registerRequest) {
    User user = prepareUserRegister(registerRequest);
    if (user == null)
      return null;
    user.setRoles(Arrays.asList(TypeOfRole.CLIENT));
    return userRepository.save(user);
  }

  public User registerCompany(RegisterRequest registerRequest) {
    User user = prepareUserRegister(registerRequest);
    if (user == null)
      return null;
    user.setRoles(Arrays.asList(TypeOfRole.COMPANY));
    return userRepository.save(user);
  }

  public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

    User user = userRepository.findByEmail(authRequest.getEmail())
        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encojtrado!"));
    var token = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .withToken(token)
        .withAuthenticatedUser(user.getPersonalData().getFirstname() + " " + user.getPersonalData().getLastname())
        .build();
  }
}
