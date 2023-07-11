package com.br.authentication_microservice.error;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResourceMethodArgumentNotValid {
  private MethodArgumentNotValidException ex;
  private HttpStatusCode httpStatus;

  public ErrorResponse getErrorResponse() {
    return ErrorResponse.builder()
        .withMessage("Campos ausentes ou inválidos!")
        .withCode(httpStatus.value())
        .withStatus(HttpStatus.valueOf(httpStatus.value()).getReasonPhrase())
        .withFields(getErrors(ex))
        .build();
  }

  private Map<String, String> getErrors(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    BindingResult bindingresult = ex.getBindingResult();
    List<FieldError> allErrors = bindingresult.getFieldErrors();
    allErrors.forEach(error -> {
      String fieldName = error.getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return errors;
  }
}
