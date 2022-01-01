package com.example.crud.demo.service;

import com.example.crud.demo.domain.DTO.PersonDTO;
import com.example.crud.demo.domain.Person;
import java.util.List;
import org.springframework.validation.BindingResult;

public interface PersonService {

  Person findById(Long id);

  List<Person> findAll();

  Person save(PersonDTO person, BindingResult result);

  Person update(PersonDTO person, BindingResult result);

  void delete(Long id);
}
