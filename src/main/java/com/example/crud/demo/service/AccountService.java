package com.example.crud.demo.service;

import com.example.crud.demo.domain.Account;
import java.util.List;

public interface AccountService {
  List<Account> findAll();
}
