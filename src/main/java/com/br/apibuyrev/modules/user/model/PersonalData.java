package com.br.apibuyrev.modules.user.model;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.br.apibuyrev.modules.auth.model.RegisterRequest;
import com.br.apibuyrev.modules.user.enums.TypeOfPerson;

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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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

  private String firstname;

  private String lastname;

  @Enumerated(EnumType.STRING)
  private TypeOfPerson typeOfperson;

  @Column(unique = true, nullable = true)
  private String cpfCnpj;

  @ElementCollection
  @CollectionTable(name = "personal_data_contacts", joinColumns = @JoinColumn(name = "personal_data_id"))
  private List<Contact> contacts;

  @OneToOne(mappedBy = "personalData")
  private User user;

  @ElementCollection
  @CollectionTable(name = "personal_data_address", joinColumns = @JoinColumn(name = "personal_data_id"))
  private List<Address> address;

  public void validateCompletionAndSetCpfCnjpByTypeOfPerson(RegisterRequest registerRequest) {
    if (registerRequest.getTypeOfPerson().equals(TypeOfPerson.PHYSICALPERSON)) {
      if (registerRequest.getCpf().isBlank())
        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "É necessário o preenchimento do CPF!");
      setCpfCnpj(registerRequest.getCpf());
    } else {
      if (registerRequest.getCnpj().isBlank())
        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "É necessário o preenchimento do CNPJ!");
      setCpfCnpj(registerRequest.getCnpj());
    }
  }
}
