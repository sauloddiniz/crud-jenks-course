package com.example.crud.demo.controller;

import com.example.crud.demo.domain.DTO.PersonDTO;
import com.example.crud.demo.domain.Person;
import com.example.crud.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getOnePerson(@PathVariable("id")Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<PersonDTO> savePerson(@Valid @RequestBody PersonDTO person, BindingResult result) {
       PersonDTO dto = service.save(person, result);
       return ResponseEntity.ok(dto);
    }
}
