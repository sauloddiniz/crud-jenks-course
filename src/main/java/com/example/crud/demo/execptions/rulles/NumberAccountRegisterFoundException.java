package com.example.crud.demo.execptions.rulles;

public class NumberAccountRegisterFoundException extends RuntimeException {
  public NumberAccountRegisterFoundException(String numberAccount) {
    super(String.format("numberAccount %s already register", numberAccount));
  }
}
