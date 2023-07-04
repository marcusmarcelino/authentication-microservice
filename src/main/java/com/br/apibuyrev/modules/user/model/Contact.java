package com.br.apibuyrev.modules.user.model;

import com.br.apibuyrev.modules.user.enums.TypeOfContact;

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
