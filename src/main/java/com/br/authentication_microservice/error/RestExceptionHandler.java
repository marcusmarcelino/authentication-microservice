package com.br.authentication_microservice.error;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
    ResourceMethodArgumentNotValid errorResponse = new ResourceMethodArgumentNotValid(ex, status);
    return new ResponseEntity<>(errorResponse.getErrorResponse(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({ BadCredentialsException.class })
  public ResponseEntity<Object> handleAccessDeniedException(
      Exception ex, WebRequest request) {

    MessageExceptionHandler messageExceptionHandler = MessageExceptionHandler.builder()
        .withTimestamp(new Date())
        .withCode(HttpStatus.FORBIDDEN.value())
        .withMessage("Acesso negado!")
        .build();

    return new ResponseEntity<Object>(messageExceptionHandler, HttpStatus.UNAUTHORIZED);
  }
}
