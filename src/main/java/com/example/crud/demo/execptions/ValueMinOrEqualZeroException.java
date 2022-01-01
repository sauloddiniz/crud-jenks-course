package com.example.crud.demo.execptions;

public class ValueMinOrEqualZeroException extends RuntimeException {
  public ValueMinOrEqualZeroException() {
    super("Value must be greater than zero");
  }
}
