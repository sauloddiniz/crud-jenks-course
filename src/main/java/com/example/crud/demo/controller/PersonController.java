package com.example.crud.demo.controller;

import com.example.crud.demo.domain.DTO.PersonDTO;
import com.example.crud.demo.domain.Person;
import com.example.crud.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getOnePerson(@PathVariable("id")Long id) {
        return ResponseEntity.ok(Person.convertDTO(service.findById(id)));
    }
}
