package com.example.crud.demo.domain;

import com.example.crud.demo.domain.DTO.PersonDTO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name = "PERSONS")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String secondName;
    private String cpf;

    public static PersonDTO convertDTO(Person person) {
        return PersonDTO.builder()
                .id(person.getId())
                .name(person.getName())
                .secondName(person.getSecondName())
                .cpf(person.getCpf())
                .build();
    }
}

