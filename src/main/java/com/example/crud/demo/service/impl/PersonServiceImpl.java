package com.example.crud.demo.service.impl;

import com.example.crud.demo.domain.Person;
import com.example.crud.demo.execptions.objectNotFoundException;
import com.example.crud.demo.repository.PersonRepository;
import com.example.crud.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository rep;

    @Override
    public Person findById(Long id) {
        return findOne(id);
    }

    private Person findOne(Long id) {
        return rep.findById(id).orElseThrow(() -> new objectNotFoundException(id));
    }
}
