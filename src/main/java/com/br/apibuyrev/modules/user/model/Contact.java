package com.br.apibuyrev.modules.user.model;

import com.br.apibuyrev.modules.user.enums.ContactType;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Contact {
  private ContactType type;
  private String contact;
}
