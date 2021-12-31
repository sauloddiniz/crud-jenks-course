package com.example.crud.demo.execptions;

import javax.persistence.EntityNotFoundException;

public class objectNotFoundException extends EntityNotFoundException {

    public objectNotFoundException(Long id) {
        super(String.format("Object id %s not found ",id));
    }

}
