package com.example.crud.demo.service.impl;

import com.example.crud.demo.domain.Account;
import com.example.crud.demo.domain.DTO.AccountDTO;
import com.example.crud.demo.execptions.rulles.NotBalanceException;
import com.example.crud.demo.execptions.rulles.NumberAccountRegisterFoundException;
import com.example.crud.demo.execptions.rulles.ObjectNotFoundException;
import com.example.crud.demo.repository.AccountRepository;
import com.example.crud.demo.service.AccountService;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

  @Autowired private AccountRepository repository;

  @Override
  public List<Account> findAll() {
    return repository.findAll();
  }

  @Override
  public Account save(Account account) {
    checkExistenceNumberAccount(account.getNumberAccount());
    return repository.save(account);
  }

  @Override
  public Account update(Account account) {
    var accountBd = findOne(account.getId());
    if (!account.getNumberAccount().equals(accountBd.getNumberAccount())) {
      checkExistenceNumberAccount(account.getNumberAccount());
    }
    return repository.save(account);
  }

  @Override
  public void delete(Long id) {
    findOne(id);
    repository.deleteById(id);
  }

  @Override
  public Account withdrawBalance(AccountDTO accountDTO) {
    var accountBd = findByNumberAccount(accountDTO.getNumberAccount());
    BigDecimal valueWithdraw = getBalanceAccount(accountDTO, accountBd);
    repository.updateBalance(accountBd.getId(), accountBd.withdrawBalance(valueWithdraw));
    return accountBd;
  }

  @Override
  public Account depositBalance(AccountDTO accountDTO) {
    var accountBd = findByNumberAccount(accountDTO.getNumberAccount());
    repository.updateBalance(accountBd.getId(), accountBd.depositBalance(accountDTO.getDeposit()));
    return accountBd;
  }

  private BigDecimal getBalanceAccount(AccountDTO accountDTO, Account accountBd) {
    if (accountBd.getBalance().compareTo(accountDTO.getWithdraw()) < 0) {
      throw new NotBalanceException(accountBd.getBalance().toString());
    }
    return accountDTO.getWithdraw();
  }

  private Account findOne(Long id) {
    return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
  }

  private void checkExistenceNumberAccount(String numberAccount) {
    if (repository.findByNumberAccount(numberAccount).isPresent())
      throw new NumberAccountRegisterFoundException(numberAccount);
  }

  private Account findByNumberAccount(String numberAccount) {
    return repository
        .findByNumberAccount(numberAccount)
        .orElseThrow(() -> new ObjectNotFoundException(numberAccount));
  }
}
