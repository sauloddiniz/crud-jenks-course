package com.example.crud.demo.domain.DTO.errors.execptions;

import javax.persistence.EntityNotFoundException;

public class ObjectNotFoundException extends EntityNotFoundException {

  public ObjectNotFoundException(Long id) {
    super(String.format("Object id %s not found ", id));
  }
}
