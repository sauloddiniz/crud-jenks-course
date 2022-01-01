package com.example.crud.demo.execptions;

public class NumberAccountRegisterFoundException extends RuntimeException {
  public NumberAccountRegisterFoundException(String numberAccount) {
    super(String.format("numberAccount %s already register", numberAccount));
  }
}
