package com.example.crud.demo.service;

import com.example.crud.demo.domain.DTO.PersonDTO;
import org.springframework.validation.BindingResult;

public interface PersonService {

    PersonDTO findById(Long id);

    PersonDTO save(PersonDTO person, BindingResult result);
}
