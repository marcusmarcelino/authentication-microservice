package com.br.apibuyrev.modules.auth.model;

import com.br.apibuyrev.modules.user.enums.TypeOfContact;
import com.br.apibuyrev.modules.user.enums.TypeOfPerson;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class RegisterRequest {
  @NotBlank(message = "{form.field.message}")
  private String firstname;

  private String lastname;

  @NotNull(message = "{form.field.message}")
  private TypeOfPerson typeOfPerson;

  private String cpf;

  private String cnpj;

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

  private String cep;

  @NotNull(message = "{form.field.message}")
  private TypeOfContact typeOfContact;

  private String contact;

  @NotBlank(message = "{form.field.message}")
  private String email;

  @NotBlank(message = "{form.field.message}")
  private String password;
}