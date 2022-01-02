package com.example.crud.demo.service;

import com.example.crud.demo.domain.DTO.PersonDTO;
import com.example.crud.demo.domain.Person;
import java.util.List;

public interface PersonService {

  Person findById(Long id);

  List<Person> findAll();

  Person save(PersonDTO person);

  Person update(PersonDTO person);

  void delete(Long id);
}
