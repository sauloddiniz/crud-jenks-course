package com.example.crud.demo.domain.DTO;

import com.example.crud.demo.domain.Account;
import com.example.crud.demo.domain.Person;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccountDTO implements Serializable {

  private Long id;

  @ManyToOne
  @JsonIgnoreProperties({"accountList"})
  private Person person;

  private String numberAccount;
  private BigDecimal balance;
  private BigDecimal withdraw;
  private BigDecimal deposit;

  public static AccountDTO converter(Account account) {
    return AccountDTO.builder()
        .id(account.getId())
        .balance(account.getBalance())
        .numberAccount(account.getNumberAccount())
        .person(account.getPerson())
        .build();
  }
}
