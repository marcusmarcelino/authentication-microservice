package com.br.authentication_microservice.modules.user.model;

import com.br.authentication_microservice.modules.user.enums.TypeOfContact;

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
public class Contact {
  private TypeOfContact type;
  private String contact;
}
