package com.example.crud.demo.execptions.rulles;

public class CpfRegisterFoundException extends RuntimeException {
  public CpfRegisterFoundException(String msg) {
    super(String.format("CPF number %s already register", msg));
  }
}
