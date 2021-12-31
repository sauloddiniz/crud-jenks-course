package com.example.crud.demo.service.impl;

import com.example.crud.demo.domain.DTO.PersonDTO;
import com.example.crud.demo.domain.DTO.errors.execptions.CpfRegisterFoundException;
import com.example.crud.demo.domain.DTO.errors.execptions.ObjectNotFoundException;
import com.example.crud.demo.domain.Person;
import com.example.crud.demo.repository.PersonRepository;
import com.example.crud.demo.service.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

  @Autowired private PersonRepository rep;

  @Override
  public Person findById(Long id) {
    return findOne(id);
  }

  public List<Person> findAll() {
    return rep.findAll();
  }

  @Override
  public Person save(PersonDTO person) {
    checkExistenceCpf(person.getCpf());
    return rep.save(Person.postConverter(person));
  }

  @Override
  public Person update(PersonDTO person) {
    Person personBd = findOne(person.getId());
    if (!person.getCpf().equals(personBd.getCpf())) {
      checkExistenceCpf(person.getCpf());
    }
    return rep.save(Person.postConverter(person));
  }

  @Override
  public void delete(Long id) {
    findOne(id);
    rep.deleteById(id);
  }

  private Person findOne(Long id) {
    return rep.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
  }

  private void checkExistenceCpf(String cpf) {
    if (rep.findByCpf(cpf).isPresent()) {
      throw new CpfRegisterFoundException(cpf);
    }
  }
}
