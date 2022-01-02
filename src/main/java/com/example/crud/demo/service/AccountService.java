package com.example.crud.demo.service;

import com.example.crud.demo.domain.Account;
import com.example.crud.demo.domain.DTO.AccountDTO;
import java.util.List;

public interface AccountService {
  List<Account> findAll();

  Account save(Account account);

  Account update(Account account);

  void delete(Long id);

  Account withdrawBalance(AccountDTO accountDTO);

  Account depositBalance(AccountDTO accountDTO);
}
