package org.example.springsecuritypractice.exception;

import org.example.springsecuritypractice.utils.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.ConnectException;
import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class CustomExceptionHandler {
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public HttpResponse<?> handleConflict(MethodArgumentNotValidException ex, WebRequest webRequest) {
    return new HttpResponse<>(
        HttpStatus.BAD_REQUEST,
        ex.getBindingResult().getAllErrors().getFirst().getDefaultMessage());
  }

  @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
  public HttpResponse<?> handleSQLIntegrityConstraintViolation(
      SQLIntegrityConstraintViolationException ex, WebRequest webRequest) {
    var message =
        switch (ex.getErrorCode()) {
          case 1062 -> "Value Already Exist";
          case 1063 -> "SYNTAX ERROR";
          default -> ex.getMessage();
        };
    return new HttpResponse<>(HttpStatus.BAD_REQUEST, message);
  }

  @ExceptionHandler(ConnectException.class)
  public HttpResponse<?> handleConflict(ConnectException ex, WebRequest webRequest) {
    return new HttpResponse<>(HttpStatus.SERVICE_UNAVAILABLE, "Connection refused");
  }
}
