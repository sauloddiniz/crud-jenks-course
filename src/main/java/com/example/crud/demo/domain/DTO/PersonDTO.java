package com.example.crud.demo.domain.DTO;

import com.example.crud.demo.domain.Account;
import com.example.crud.demo.domain.Person;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDTO implements Serializable {

  private Long id;

  @NotBlank private String name;
  private String secondName;
  @NotBlank private String cpf;

  @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
  @JsonIgnoreProperties({"person"})
  private List<AccountDTO> accountList;

  public static PersonDTO postConverter(Person person) {

    return PersonDTO.builder()
        .id(person.getId())
        .name(person.getName())
        .secondName(person.getSecondName())
        .cpf(person.getCpf())
        .build();
  }

  public static PersonDTO getConverter(Person person) {
    return PersonDTO.builder()
        .id(person.getId())
        .name(person.getName())
        .secondName(person.getSecondName())
        .cpf(person.getCpf())
        .accountList(converterListDTO(person.getAccountList()))
        .build();
  }

  private static List<AccountDTO> converterListDTO(List<Account> accountList) {
    return accountList.stream().map(AccountDTO::converter).collect(Collectors.toList());
  }
}
