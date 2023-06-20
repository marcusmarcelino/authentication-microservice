package com.br.apibuyrev.modules.user.model;

import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "users")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
  @Id
  @GeneratedValue
  @Column(unique = true, nullable = false)
  @EqualsAndHashCode.Include
  private UUID id;

  @NotBlank
  @NonNull
  @Column(unique = true, nullable = false)
  private String email;

  @NotBlank
  @NonNull
  @Column(nullable = false)
  private String password;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "id_personal_data", referencedColumnName = "id")
  private PersonalData personalData;
}
