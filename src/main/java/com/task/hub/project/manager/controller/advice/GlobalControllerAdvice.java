package com.task.hub.project.manager.controller.advice;

import com.task.hub.project.manager.service.exceptions.NotFoundException;
import com.task.hub.project.manager.service.exceptions.SenhaInvalidaException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String, String> handleConstraintViolationError(
      ConstraintViolationException exception) {
    Map<String, String> errors = new HashMap<>();
    for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
      errors.put(violation.getPropertyPath().toString(), violation.getMessage());
    }
    return errors;
  }

  // validação da dto
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String, String> handleMethodArgumentNotValidError(
      MethodArgumentNotValidException exception) {
    Map<String, String> errors = new HashMap<>();
    for (FieldError error : exception.getBindingResult().getFieldErrors()) {
      errors.put(error.getField(), error.getDefaultMessage());
    }
    return errors;
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String handleNotFoundException(NotFoundException e) {
    return e.getMessage();
  }

  @ExceptionHandler(SenhaInvalidaException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String handleInvalidPassword(RuntimeException e) {
    return e.getMessage();
  }
}
