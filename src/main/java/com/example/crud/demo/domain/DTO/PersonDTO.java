package com.example.crud.demo.domain.DTO;

import com.example.crud.demo.domain.Person;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class PersonDTO implements Serializable {

    private Long id;
    private String name;
    private String secondName;
    private String cpf;

    public static Person convert(PersonDTO dto) {
        return Person.builder()
                .id(dto.getId())
                .name(dto.getName())
                .secondName(dto.getSecondName())
                .cpf(dto.getCpf())
                .build();
    }
}
