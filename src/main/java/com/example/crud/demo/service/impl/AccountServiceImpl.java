package com.example.crud.demo.service.impl;

import com.example.crud.demo.domain.Account;
import com.example.crud.demo.repository.AccountRepository;
import com.example.crud.demo.service.AccountService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

  @Autowired private AccountRepository reposiry;

  @Override
  public List<Account> findAll() {
    return reposiry.findAll();
  }
}
