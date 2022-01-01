package com.example.crud.demo.execptions;

import javax.persistence.EntityNotFoundException;

public class ObjectNotFoundException extends EntityNotFoundException {

  public ObjectNotFoundException(Long id) {
    super(String.format("Object id %s not found ", id));
  }

  public ObjectNotFoundException(String numberAccount) {
    super(String.format("Object id %s not found ", numberAccount));
  }
}
