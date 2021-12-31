package com.example.crud.demo.domain;

import com.example.crud.demo.domain.DTO.AccountDTO;
import com.example.crud.demo.domain.DTO.PersonDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.*;
import lombok.*;

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

  @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
  @JsonIgnoreProperties({"person"})
  private List<Account> accountList;

  public static Person getConverter(PersonDTO dto) {

    return Person.builder()
        .id(dto.getId())
        .name(dto.getName())
        .secondName(dto.getSecondName())
        .cpf(dto.getCpf())
        .build();
  }

  public static Person postConverter(PersonDTO dto) {

    return Person.builder()
        .id(dto.getId())
        .name(dto.getName())
        .secondName(dto.getSecondName())
        .cpf(dto.getCpf())
        .build();
  }

  private static List<Account> converterListAccountDTO(List<AccountDTO> dtoList) {
    return dtoList.stream().map(Account::converterDTO).collect(Collectors.toList());
  }
}
