package com.example.crud.demo.domain.DTO;

import com.example.crud.demo.domain.Account;
import com.example.crud.demo.domain.Person;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
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

  @Min(0)
  private BigDecimal balance;

  @Min(1)
  private BigDecimal withdraw;

  @Min(1)
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
