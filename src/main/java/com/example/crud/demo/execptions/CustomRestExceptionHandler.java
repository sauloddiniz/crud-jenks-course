package com.example.crud.demo.execptions;

import com.example.crud.demo.domain.DTO.errors.ResponseErrorApiDTO;
import com.example.crud.demo.execptions.rulles.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ResponseErrorApiDTO> handlerEntityNotFoundException(
      EntityNotFoundException entity) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    String message = entity.getLocalizedMessage();
    ResponseErrorApiDTO errorApiDTO = getResponseErrorApiDTO(message, status);
    return new ResponseEntity<>(errorApiDTO, status);
  }

  @ExceptionHandler(CpfRegisterFoundException.class)
  public ResponseEntity<ResponseErrorApiDTO> handlerCpfAlreadyRegister(
      CpfRegisterFoundException msg) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    String message = msg.getLocalizedMessage();
    ResponseErrorApiDTO errorApiDTO = getResponseErrorApiDTO(message, status);
    return new ResponseEntity<>(errorApiDTO, status);
  }

  @ExceptionHandler(NumberAccountRegisterFoundException.class)
  public ResponseEntity<ResponseErrorApiDTO> handlerAccountNumberAlreadyRegister(
      NumberAccountRegisterFoundException msg) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    String message = msg.getLocalizedMessage();
    ResponseErrorApiDTO errorApiDTO = getResponseErrorApiDTO(message, status);
    return new ResponseEntity<>(errorApiDTO, status);
  }

  @ExceptionHandler(NotBalanceException.class)
  public ResponseEntity<ResponseErrorApiDTO> handlerAccountNumberAlreadyRegister(
      NotBalanceException msg) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    String message = msg.getLocalizedMessage();
    ResponseErrorApiDTO errorApiDTO = getResponseErrorApiDTO(message, status);
    return new ResponseEntity<>(errorApiDTO, status);
  }

  @ExceptionHandler(ValueMinOrEqualZeroException.class)
  public ResponseEntity<ResponseErrorApiDTO> handlerValueMinOrEqualZeroException(
      ValueMinOrEqualZeroException msg) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    String message = msg.getLocalizedMessage();
    ResponseErrorApiDTO errorApiDTO = getResponseErrorApiDTO(message, status);
    return new ResponseEntity<>(errorApiDTO, status);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    List<String> errors = new ArrayList<String>();
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.add(error.getField() + ": " + error.getDefaultMessage());
    }
    for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
      errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
    }

    ResponseErrorApiDTO errorApiDTO =
        ResponseErrorApiDTO.builder()
            .error(status.value())
            .timestamp(LocalDateTime.now())
            .status(status)
            .errorsFields(errors)
            .build();

    return handleExceptionInternal(ex, errorApiDTO, headers, httpStatus, request);
  }

  private ResponseErrorApiDTO getResponseErrorApiDTO(String message, HttpStatus status) {
    return ResponseErrorApiDTO.builder()
        .error(status.value())
        .timestamp(LocalDateTime.now())
        .status(status)
        .message(message)
        .build();
  }
}
