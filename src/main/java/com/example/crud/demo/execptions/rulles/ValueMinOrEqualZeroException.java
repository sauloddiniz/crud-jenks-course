package com.example.crud.demo.execptions.rulles;

public class ValueMinOrEqualZeroException extends RuntimeException {
  public ValueMinOrEqualZeroException() {
    super("Value must be greater than zero");
  }
}
