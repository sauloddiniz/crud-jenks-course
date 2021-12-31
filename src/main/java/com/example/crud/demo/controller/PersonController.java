package com.example.crud.demo.controller;

import com.example.crud.demo.controller.fields.FieldsHasErrors;
import com.example.crud.demo.domain.DTO.PersonDTO;
import com.example.crud.demo.domain.Person;
import com.example.crud.demo.service.PersonService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

  @Autowired private PersonService service;

  @GetMapping("/{id}")
  public ResponseEntity<PersonDTO> getOnePerson(@PathVariable("id") Long id) {
    return ResponseEntity.ok(PersonDTO.getConverter(service.findById(id)));
  }

  @GetMapping
  public ResponseEntity<List<PersonDTO>> findAll() {
    List<PersonDTO> dtoList =
        service.findAll().stream().map(PersonDTO::getConverter).collect(Collectors.toList());
    return dtoList.isEmpty()
        ? ResponseEntity.noContent().build()
        : ResponseEntity.ok().body(dtoList);
  }

  @PostMapping
  public ResponseEntity<PersonDTO> savePerson(
      @Valid @RequestBody PersonDTO person, BindingResult result) {
    FieldsHasErrors.fieldsHasErrors(result);
    Person saved = service.save(person);
    return ResponseEntity.ok(PersonDTO.postConverter(saved));
  }

  @PutMapping
  public ResponseEntity<PersonDTO> updatePerson(
      @Valid @RequestBody PersonDTO person, BindingResult result) {
    FieldsHasErrors.fieldsHasErrors(result);
    Person saved = service.update(person);
    return ResponseEntity.ok(PersonDTO.postConverter(saved));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<PersonDTO> deletePerson(@PathVariable("id") Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
