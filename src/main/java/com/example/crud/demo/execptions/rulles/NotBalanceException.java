package com.example.crud.demo.execptions.rulles;

public class NotBalanceException extends RuntimeException {
  public NotBalanceException(String msg) {
    super(String.format("Sorry you have no balance %s", msg));
  }
}
