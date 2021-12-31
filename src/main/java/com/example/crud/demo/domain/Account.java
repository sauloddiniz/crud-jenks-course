package com.example.crud.demo.domain;

import com.example.crud.demo.domain.DTO.AccountDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import lombok.*;

@EqualsAndHashCode(of = "id")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ACCOUNTS")
public class Account implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "idPerson")
  @JsonIgnoreProperties({"accountList"})
  private Person person;

  private String numberAccount;
  private BigDecimal balance;

  public static Account converterDTO(AccountDTO dto) {
    return Account.builder()
        .id(dto.getId())
        .balance(dto.getBalance())
        .numberAccount(dto.getNumberAccount())
        .person(dto.getPerson())
        .build();
  }
}
