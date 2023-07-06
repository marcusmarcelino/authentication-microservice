package com.br.apibuyrev.error;

import java.util.Date;

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
public class FullMessageExceptionHandler {
  private Date timestamp;
  private Integer code;
  private String error;
  private String message;
  private String path;
}
