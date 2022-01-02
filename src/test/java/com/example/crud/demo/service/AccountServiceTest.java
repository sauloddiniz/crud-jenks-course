package com.example.crud.demo.service;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.example.crud.demo.domain.Account;
import com.example.crud.demo.repository.AccountRepository;
import com.example.crud.demo.service.impl.AccountServiceImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class AccountServiceTest {

  @Mock private AccountRepository repository;

  private AccountService service;

  private List<Account> accountList;

  @BeforeEach
  void init() {

    service = new AccountServiceImpl(repository);

    accountList = new ArrayList<>();

    accountList.add(Account.builder()
            .id(null)
            .person(null)
            .balance(BigDecimal.ZERO)
            .numberAccount("001-1")
            .build());

    accountList.add(Account.builder()
            .id(null)
            .person(null)
            .balance(BigDecimal.ZERO)
            .numberAccount("002-1")
            .build());
  }

  @Test
  @DisplayName("should_get_list_not_empty")
  void should_get_list_not_empty() {

    Account account = Account.builder()
            .id(null)
            .person(null)
            .balance(BigDecimal.ZERO)
            .numberAccount("002")
            .build();

    when(repository.findAll()).thenReturn(accountList);

    List<Account> listFromService = service.findAll();

    assertThat(listFromService, hasItem(account));
    assertThat(accountList, is(listFromService));
  }

  @Test
  @DisplayName("should_get_list_empty")
  void should_get_list_empty() {

    when(repository.findAll()).thenReturn(new ArrayList<>());
    List<Account> listFromService = service.findAll();
    assertThat(listFromService, IsEmptyCollection.empty());
  }

  @Test
  @DisplayName("should_save_success")
  void should_save_success() {

    Account account = Account.builder().id(null).person(null).balance(BigDecimal.ZERO).numberAccount("001-1").build();

    when(repository.save(Mockito.any(Account.class))).then(new Answer<Account>() {
      final Long idAccount = 1L;
      @Override
      public Account answer(InvocationOnMock invocation) throws Throwable {
        Account account = (Account) invocation.getArgument(0);
        account.setId(idAccount);
        return account;
      }
    });
    when(repository.findByNumberAccount(account.getNumberAccount())).thenReturn(Optional.empty());

    service.save(account);

    assertNotNull(account.getId());
  }
}
