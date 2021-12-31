package com.example.crud.demo.service.impl;

import com.example.crud.demo.domain.DTO.PersonDTO;
import com.example.crud.demo.domain.Person;
import com.example.crud.demo.execptions.FieldErrorsExceptions;
import com.example.crud.demo.execptions.objectNotFoundException;
import com.example.crud.demo.repository.PersonRepository;
import com.example.crud.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository rep;

    @Override
    public PersonDTO findById(Long id) {
        return Person.convertDTO(findOne(id));
    }

    @Override
    public PersonDTO save(PersonDTO person, BindingResult result) {
        if (result.hasErrors()) {
            HashMap<String,String> errors = new HashMap<>();
            for(FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(),result.getFieldErrors(error.getField()).stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining(",")));
            }
            throw new FieldErrorsExceptions(errors.toString());
        }
        return null;
    }

    private Person findOne(Long id) {
        return rep.findById(id).orElseThrow(() -> new objectNotFoundException(id));
    }
}
