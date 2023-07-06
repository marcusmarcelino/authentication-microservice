package com.br.apibuyrev.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
  @Override
  @Nullable
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    // Map<String, String> errors = new HashMap<>();
    // ex.getBindingResult().getAllErrors().forEach((error) -> {
    // String fieldName = ((FieldError) error).getField();
    // String errorMessage = error.getDefaultMessage();
    // errors.put(fieldName, errorMessage);
    // });

    ResourceMethodArgumentNotValid errorResponse = new ResourceMethodArgumentNotValid(ex, status);
    return new ResponseEntity<>(errorResponse.getErrorResponse(), HttpStatus.BAD_REQUEST);
  }

}
