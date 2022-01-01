package com.example.crud.demo.execptions;

import com.example.crud.demo.controller.fields.FieldsErrors;
import com.example.crud.demo.domain.DTO.errors.ResponseErrorApiDTO;
import com.example.crud.demo.execptions.rulles.*;
import java.time.LocalDateTime;
import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ResponseErrorApiDTO> handlerEntityNotFoundException(
      EntityNotFoundException entity) {
    HttpStatus status = HttpStatus.NOT_FOUND;

    ResponseErrorApiDTO errorApiDTO =
        ResponseErrorApiDTO.builder()
            .error(status.value())
            .timestamp(LocalDateTime.now())
            .status(status)
            .message(entity.getLocalizedMessage())
            .build();
    return new ResponseEntity<>(errorApiDTO, status);
  }

  @ExceptionHandler(CpfRegisterFoundException.class)
  public ResponseEntity<ResponseErrorApiDTO> handlerCpfAlreadyRegister(
      CpfRegisterFoundException msg) {
    HttpStatus status = HttpStatus.BAD_REQUEST;

    ResponseErrorApiDTO errorApiDTO =
        ResponseErrorApiDTO.builder()
            .error(status.value())
            .timestamp(LocalDateTime.now())
            .status(status)
            .message(msg.getLocalizedMessage())
            .build();
    return new ResponseEntity<>(errorApiDTO, status);
  }

  @ExceptionHandler(NumberAccountRegisterFoundException.class)
  public ResponseEntity<ResponseErrorApiDTO> handlerAccountNumberAlreadyRegister(
      NumberAccountRegisterFoundException msg) {
    HttpStatus status = HttpStatus.BAD_REQUEST;

    ResponseErrorApiDTO errorApiDTO =
        ResponseErrorApiDTO.builder()
            .error(status.value())
            .timestamp(LocalDateTime.now())
            .status(status)
            .message(msg.getLocalizedMessage())
            .build();
    return new ResponseEntity<>(errorApiDTO, status);
  }

  @ExceptionHandler(NotBalanceException.class)
  public ResponseEntity<ResponseErrorApiDTO> handlerAccountNumberAlreadyRegister(
      NotBalanceException msg) {
    HttpStatus status = HttpStatus.BAD_REQUEST;

    ResponseErrorApiDTO errorApiDTO =
        ResponseErrorApiDTO.builder()
            .error(status.value())
            .timestamp(LocalDateTime.now())
            .status(status)
            .message(msg.getLocalizedMessage())
            .build();
    return new ResponseEntity<>(errorApiDTO, status);
  }

  @ExceptionHandler(ValueMinOrEqualZeroException.class)
  public ResponseEntity<ResponseErrorApiDTO> handlerAccountNumberAlreadyRegister(
      ValueMinOrEqualZeroException msg) {
    HttpStatus status = HttpStatus.BAD_REQUEST;

    ResponseErrorApiDTO errorApiDTO =
        ResponseErrorApiDTO.builder()
            .error(status.value())
            .timestamp(LocalDateTime.now())
            .status(status)
            .message(msg.getLocalizedMessage())
            .build();
    return new ResponseEntity<>(errorApiDTO, status);
  }

  @ExceptionHandler(FieldErrorsExceptions.class)
  public ResponseEntity<ResponseErrorApiDTO> handlerFieldErrorsException(
      FieldErrorsExceptions msg) {
    HttpStatus status = HttpStatus.BAD_REQUEST;

    ResponseErrorApiDTO errorApiDTO =
        ResponseErrorApiDTO.builder()
            .error(status.value())
            .timestamp(LocalDateTime.now())
            .status(status)
            .errorsFields(FieldsErrors.mountErrorMessage(msg))
            .build();
    return new ResponseEntity<>(errorApiDTO, status);
  }
}
