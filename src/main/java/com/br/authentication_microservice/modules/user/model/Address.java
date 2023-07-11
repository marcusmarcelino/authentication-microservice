package com.br.authentication_microservice.modules.user.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Address {
  private String district;
  private String road;
  private String number;
  private String complement;
  private String city;
  private String uf;
}
