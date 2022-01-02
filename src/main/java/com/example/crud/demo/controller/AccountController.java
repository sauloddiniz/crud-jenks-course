package com.example.crud.demo.controller;

import com.example.crud.demo.controller.fields.FieldsErrors;
import com.example.crud.demo.domain.Account;
import com.example.crud.demo.domain.DTO.AccountDTO;
import com.example.crud.demo.service.AccountService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {

  @Autowired private AccountService service;

  @GetMapping
  public ResponseEntity<List<AccountDTO>> getAllAccounts() {
    log.info("Get list accounts");
    return ResponseEntity.ok(
        service.findAll().stream().map(AccountDTO::converter).collect(Collectors.toList()));
  }

  @PostMapping
  public ResponseEntity<AccountDTO> saveAccount(
      @Valid @RequestBody AccountDTO accountDTO, BindingResult result) {
    FieldsErrors.fieldsHasErrors(result);
    var account = service.save(Account.converterDTO(accountDTO));
    return ResponseEntity.ok().body(AccountDTO.converter(account));
  }

  @PutMapping("/{id}")
  public ResponseEntity<AccountDTO> updateAccount(
      @PathVariable("id") Long id,
      @Valid @RequestBody AccountDTO accountDTO,
      BindingResult result) {
    FieldsErrors.fieldsHasErrors(result);
    var account = service.update(Account.converterDTO(accountDTO));
    return ResponseEntity.ok().body(AccountDTO.converter(account));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<AccountDTO> deleteAccount(@PathVariable("id") Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}/withdraw")
  public ResponseEntity<AccountDTO> withdrawBalance(
      @RequestParam("numberAccount") String numberAccount, @RequestBody AccountDTO accountDTO) {
    accountDTO.setNumberAccount(numberAccount);
    return ResponseEntity.ok().body(AccountDTO.converter(service.withdrawBalance(accountDTO)));
  }

  @PutMapping("/deposit")
  public ResponseEntity<AccountDTO> depositBalance(
      @RequestParam("numberAccount") String numberAccount, @RequestBody AccountDTO accountDTO) {
    accountDTO.setNumberAccount(numberAccount);
    return ResponseEntity.ok().body(AccountDTO.converter(service.depositBalance(accountDTO)));
  }
}
