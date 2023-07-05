package com.br.apibuyrev.modules.auth.model;

import com.br.apibuyrev.modules.user.enums.TypeOfContact;
import com.br.apibuyrev.modules.user.enums.TypeOfPerson;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RegisterRequest {
  @NotBlank(message = "{form.field.message}")
  private String firstname;

  private String lastname;

  private TypeOfPerson typeOfPerson;

  @NotBlank(message = "{form.field.message}")
  private String cpfCnpj;

  @NotBlank(message = "{form.field.message}")
  private String district;

  private String road;
  private String number;

  @NotBlank(message = "{form.field.message}")
  private String neighborhood;

  @NotBlank(message = "{form.field.message}")
  private String city;

  @NotBlank(message = "{form.field.message}")
  private String uf;

  @NotBlank(message = "{form.field.message}")
  private String complement;

  @NotBlank(message = "{form.field.message}")
  private String cep;

  private TypeOfContact typeOfContact;

  private String contact;

  @NotBlank(message = "{form.field.message}")
  private String email;

  @NotBlank(message = "{form.field.message}")
  private String password;
}