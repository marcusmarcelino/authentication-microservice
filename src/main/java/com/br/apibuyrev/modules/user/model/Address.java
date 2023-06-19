package com.br.apibuyrev.modules.user.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Address {
  private String district;
  private String road;
  private String number;
  private String complement;
  private String city;
  private String uf;
}
