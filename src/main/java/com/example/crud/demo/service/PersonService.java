package com.example.crud.demo.service;

import com.example.crud.demo.domain.Person;

public interface PersonService {

    Person findById(Long id);
}
