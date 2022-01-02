package com.example.crud.demo.service;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import com.example.crud.demo.domain.Account;
import com.example.crud.demo.repository.AccountRepository;
import com.example.crud.demo.service.impl.AccountServiceImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class AccountServiceTest {

  @Mock private AccountRepository repository;

  private AccountService service;

  @BeforeEach
  void init() {
    service = new AccountServiceImpl(repository);
  }

  @Test
  @DisplayName("should_get_list_not_empty")
  void should_get_list_not_empty() {

    List<Account> accountList = new ArrayList<>();

    Account accountA =
        Account.builder()
            .id(null)
            .person(null)
            .balance(BigDecimal.ZERO)
            .numberAccount("001")
            .build();

    Account accountB =
        Account.builder()
            .id(null)
            .person(null)
            .balance(BigDecimal.ZERO)
            .numberAccount("002")
            .build();

    accountList.add(accountA);
    accountList.add(accountB);

    when(repository.findAll()).thenReturn(accountList);

    List<Account> listFromService = service.findAll();

    assertThat(listFromService, hasItem(accountB));
    assertThat(accountList, is(listFromService));
  }

  @Test
  @DisplayName("should_get_list_empty")
  void should_get_list_empty() {

    when(repository.findAll()).thenReturn(new ArrayList<>());
    List<Account> listFromService = service.findAll();
    assertThat(listFromService, IsEmptyCollection.empty());
  }
}
