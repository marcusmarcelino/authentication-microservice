package com.br.apibuyrev.modules.user.model;

import java.util.List;
import java.util.UUID;

import com.br.apibuyrev.modules.user.enums.PersonalType;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "personal_data")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PersonalData {
  @Id
  @GeneratedValue
  @EqualsAndHashCode.Include
  @Column(unique = true, nullable = false)
  private UUID id;

  @NotBlank
  @NonNull
  private String firstname;

  @NotBlank
  @NonNull
  private String lastname;

  private PersonalType type;

  @Column(unique = true, nullable = true)
  private String cpf;

  @Column(unique = true, nullable = true)
  private String cnpj;

  @ElementCollection
  @CollectionTable(name = "personal_data_contacts", joinColumns = @JoinColumn(name = "personal_data_id"))
  private List<Contact> contacts;

  @OneToOne(mappedBy = "personalData")
  private User user;

  @ElementCollection
  @CollectionTable(name = "personal_data_address", joinColumns = @JoinColumn(name = "personal_data_id"))
  private List<Address> address;
}
