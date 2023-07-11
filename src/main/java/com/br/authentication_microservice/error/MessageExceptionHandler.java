package com.br.authentication_microservice.error;

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
public class MessageExceptionHandler {
  private Date timestamp;
  private Integer code;
  private String message;
}
