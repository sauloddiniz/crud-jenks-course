package com.example.crud.demo.service;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.example.crud.demo.domain.Account;
import com.example.crud.demo.execptions.rulles.NotBalanceException;
import com.example.crud.demo.execptions.rulles.NumberAccountRegisterFoundException;
import com.example.crud.demo.execptions.rulles.ObjectNotFoundException;
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
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest(AccountServiceImpl.class)
class AccountServiceTest {

  @Mock private AccountRepository repository;

  private AccountService service;

  private List<Account> accountList;
  private Account account;

  @BeforeEach
  void init() {

    service = PowerMockito.spy(new AccountServiceImpl(repository));

    accountList = new ArrayList<>();

    accountList.add(
        Account.builder()
            .id(null)
            .person(null)
            .balance(BigDecimal.ZERO)
            .numberAccount("001-1")
            .build());

    accountList.add(
        Account.builder()
            .id(null)
            .person(null)
            .balance(BigDecimal.ZERO)
            .numberAccount("002-1")
            .build());

    account =
        Account.builder()
            .id(null)
            .person(null)
            .balance(BigDecimal.ZERO)
            .numberAccount("001-1")
            .build();
  }

  @Test
  @DisplayName("should_get_list_not_empty")
  void should_get_list_not_empty() {

    Account account =
        Account.builder()
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

    when(repository.save(Mockito.any(Account.class)))
        .then(
            new Answer<Account>() {
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

  @Test
  @DisplayName("should_save_number_account_register")
  void should_save_number_account_register() {
    String message = String.format("numberAccount %s already register", account.getNumberAccount());

    when(repository.findByNumberAccount(account.getNumberAccount()))
        .thenReturn(Optional.of(accountList.get(0)));

    NumberAccountRegisterFoundException th =
        assertThrows(NumberAccountRegisterFoundException.class, () -> service.save(account));

    assertEquals(message, th.getMessage());
  }

  @Test
  @DisplayName("should_update_success")
  void should_update_success() {

    Account newAccount =
        Account.builder().id(1L).numberAccount("001-01").balance(BigDecimal.TEN).build();

    Account bdAccount =
        Account.builder().id(1L).numberAccount("001-01").balance(BigDecimal.ZERO).build();

    when(repository.findById(1L)).thenReturn(Optional.of(bdAccount));

    when(repository.save(newAccount)).thenReturn(newAccount);

    service.update(newAccount);

    assertEquals(BigDecimal.TEN, newAccount.getBalance());
  }

  @Test
  @DisplayName("should_update_number_account_register")
  void should_update_number_account_register() {

    Account newAccount =
        Account.builder().id(1L).numberAccount("001-03").balance(BigDecimal.TEN).build();
    Account bdAccount =
        Account.builder().id(1L).numberAccount("001-01").balance(BigDecimal.ZERO).build();
    Account numberAccountExist =
        Account.builder().id(1L).numberAccount("001-03").balance(BigDecimal.ZERO).build();
    String message =
        String.format("numberAccount %s already register", numberAccountExist.getNumberAccount());

    when(repository.findById(1L)).thenReturn(Optional.of(bdAccount));

    when(repository.findByNumberAccount(newAccount.getNumberAccount()))
        .thenReturn(Optional.of(numberAccountExist));

    NumberAccountRegisterFoundException th =
        assertThrows(NumberAccountRegisterFoundException.class, () -> service.update(newAccount));

    assertEquals(message, th.getMessage());
  }

  @Test
  @DisplayName("should_find_one_error")
  void should_find_one_error() throws Exception {

    Long id = 1L;
    String message = String.format("Object id %s not found ", id);

    when(repository.findById(id)).thenReturn(Optional.empty());

    ObjectNotFoundException th =
        assertThrows(
            ObjectNotFoundException.class,
            () ->
                PowerMockito.when(service, "findOne", id)
                    .thenThrow(new ObjectNotFoundException(id)));

    assertTrue(message.contains(th.getMessage()));
  }

  @Test
  @DisplayName("should_number_account_present")
  void should_number_account_present() throws Exception {

    Account account =
        Account.builder().id(1L).numberAccount("001-03").balance(BigDecimal.TEN).build();
    String message = String.format("numberAccount %s already register", account.getNumberAccount());

    when(repository.findByNumberAccount(account.getNumberAccount()))
        .thenReturn(Optional.of(account));

    NumberAccountRegisterFoundException th =
        assertThrows(
            NumberAccountRegisterFoundException.class,
            () ->
                PowerMockito.when(
                        service, "checkExistenceNumberAccount", account.getNumberAccount())
                    .thenThrow(
                        new NumberAccountRegisterFoundException(account.getNumberAccount())));

    assertTrue(message.contains(th.getMessage()));
  }

  @Test
  @DisplayName("should_number_account_not_present")
  void should_number_account_not_present() throws Exception {

    Account account =
        Account.builder().id(1L).numberAccount("001-03").balance(BigDecimal.TEN).build();

    when(repository.findByNumberAccount(account.getNumberAccount())).thenReturn(Optional.empty());

    PowerMockito.when(service, "checkExistenceNumberAccount", account.getNumberAccount())
        .thenReturn(null);

    assertDoesNotThrow(() -> new NumberAccountRegisterFoundException(account.getNumberAccount()));
  }

  @Test
  @DisplayName("should_not_find_number_account")
  void should_not_find_number_account() throws Exception {

    Account account =
        Account.builder().id(1L).numberAccount("001-03").balance(BigDecimal.TEN).build();
    String message = String.format("Object id %s not found ", account.getNumberAccount());

    when(repository.findByNumberAccount(account.getNumberAccount())).thenReturn(Optional.empty());

    ObjectNotFoundException th =
        assertThrows(
            ObjectNotFoundException.class,
            () ->
                PowerMockito.when(service, "findByNumberAccount", account.getNumberAccount())
                    .thenThrow(new ObjectNotFoundException(account.getNumberAccount())));

    assertTrue(message.contains(th.getMessage()));
  }

  @Test
  @DisplayName("should_find_number_account")
  void should_find_number_account() throws Exception {

    Account account =
        Account.builder().id(1L).numberAccount("001-03").balance(BigDecimal.TEN).build();

    when(repository.findByNumberAccount(account.getNumberAccount()))
        .thenReturn(Optional.of(account));

    PowerMockito.when(service, "findByNumberAccount", account.getNumberAccount())
        .thenReturn(Optional.of(account));

    assertDoesNotThrow(() -> new ObjectNotFoundException(account.getNumberAccount()));
  }

  @Test
  @DisplayName("should_get_balance_error")
  void should_get_balance_error() throws Exception {

    BigDecimal balance = BigDecimal.ZERO;
    BigDecimal withdraw = BigDecimal.TEN;

    String message = String.format("Sorry you have no balance %s", balance);

    NotBalanceException th =
        assertThrows(
            NotBalanceException.class,
            () ->
                PowerMockito.when(service, "getBalanceAccount", withdraw, balance)
                    .thenThrow(new NotBalanceException(withdraw.toString())));

    assertTrue(message.contains(th.getMessage()));
  }
}
