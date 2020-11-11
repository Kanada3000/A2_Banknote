package com.myproject.banknote.repo;

import com.myproject.banknote.entities.BanknoteMachine;
import com.myproject.banknote.entities.Currency;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface BanknoteMachineRepository extends JpaRepository<BanknoteMachine, UUID> {
    @Query("SELECT b FROM BanknoteMachine b WHERE b.currency = :currency")
    BanknoteMachine findBanknoteMachineByCurrency(@Param("currency") Currency currency);

    @Query("SELECT b FROM BanknoteMachine b WHERE b.productionSpeed = :speed")
    BanknoteMachine findBanknoteMachineBySpeed(@Param("speed") int productionSpeed);

    @Query("SELECT b FROM BanknoteMachine b WHERE b.id = :id")
    BanknoteMachine findBanknoteMachineById(@Param("id") UUID id);
}