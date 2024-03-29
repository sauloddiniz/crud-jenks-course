package com.example.crud.demo.repository;

import com.example.crud.demo.domain.Person;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
  Optional<Person> findByCpf(String cpf);
}
