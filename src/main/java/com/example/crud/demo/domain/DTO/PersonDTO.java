package com.example.crud.demo.domain.DTO;

import com.example.crud.demo.domain.Person;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class PersonDTO implements Serializable {

    private Long id;
    @NotBlank @Min(10)
    private String name;
    @NotBlank @Max(10)
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
