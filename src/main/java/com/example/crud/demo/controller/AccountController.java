package com.example.crud.demo.controller;

import com.example.crud.demo.domain.DTO.AccountDTO;
import com.example.crud.demo.service.AccountService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

  @Autowired private AccountService service;

  @GetMapping
  public ResponseEntity<List<AccountDTO>> getAllAccounts() {
    return ResponseEntity.ok(
        service.findAll().stream().map(AccountDTO::converter).collect(Collectors.toList()));
  }
}
