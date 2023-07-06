package com.br.apibuyrev.error;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class ErrorResponse {
  private String message;
  private Integer code;
  private String status;
  private Map<String, String> fields;
}
