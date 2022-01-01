package com.example.crud.demo.repository;

import com.example.crud.demo.domain.Account;
import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
  Optional<Account> findByNumberAccount(String numberAccount);

  @Modifying
  @Transactional
  @Query("UPDATE Account a SET a.balance = ?2 WHERE a.id = ?1")
  void updateBalance(Long id, BigDecimal balance);
}
