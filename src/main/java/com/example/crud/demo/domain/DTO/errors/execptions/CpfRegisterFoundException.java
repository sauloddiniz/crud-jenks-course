package com.example.crud.demo.domain.DTO.errors.execptions;

public class CpfRegisterFoundException extends RuntimeException {
  public CpfRegisterFoundException(String msg) {
    super(String.format("CPF number %s already register", msg));
  }
}
