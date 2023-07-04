package com.br.apibuyrev.modules.auth.model;

import com.br.apibuyrev.modules.user.enums.TypeOfContact;
import com.br.apibuyrev.modules.user.enums.TypeOfPerson;

import lombok.Getter;

@Getter
public class RegisterRequest {
  private String firstname;
  private String lastname;
  private TypeOfPerson typeOfPerson;
  private String cpf;
  private String cnpj;
  private String district;
  private String road;
  private String number;
  private String complement;
  private String city;
  private String uf;
  private TypeOfContact typeOfContact;
  private String contact;
  private String email;
  private String password;
}