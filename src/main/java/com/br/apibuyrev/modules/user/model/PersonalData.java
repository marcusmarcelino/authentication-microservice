package com.br.apibuyrev.modules.user.model;

import java.util.List;
import java.util.UUID;

import com.br.apibuyrev.modules.auth.model.RegisterRequest;
import com.br.apibuyrev.modules.user.enums.TypeOfPerson;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
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

  @Enumerated(EnumType.STRING)
  private TypeOfPerson typeOfperson;

  @Column(unique = true, nullable = true)
  private String cpfCnpj;

  @ElementCollection
  @CollectionTable(name = "personal_data_contacts", joinColumns = @JoinColumn(name = "personal_data_id"))
  private List<Contact> contacts;

  @JsonIgnore
  @OneToOne(mappedBy = "personalData")
  private User user;

  @ElementCollection
  @CollectionTable(name = "personal_data_address", joinColumns = @JoinColumn(name = "personal_data_id"))
  private List<Address> address;

  public void setByTypeOfPerson(RegisterRequest registerRequest) {
    if (registerRequest.getTypeOfPerson().equals(TypeOfPerson.PHYSICALPERSON)) {
      setCpfCnpj(registerRequest.getCpf());
    } else {
      setCpfCnpj(registerRequest.getCnpj());
    }
  }
}
