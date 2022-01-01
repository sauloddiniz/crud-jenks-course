package com.example.crud.demo.service;

import com.example.crud.demo.domain.Account;
import com.example.crud.demo.domain.DTO.AccountDTO;
import java.util.List;
import org.springframework.validation.BindingResult;

public interface AccountService {
  List<Account> findAll();

  Account save(Account account, BindingResult result);

  Account update(Account account, BindingResult result);

  void delete(Long id);

  Account withdrawBalance(AccountDTO accountDTO);

  Account depositBalance(AccountDTO accountDTO);
}
