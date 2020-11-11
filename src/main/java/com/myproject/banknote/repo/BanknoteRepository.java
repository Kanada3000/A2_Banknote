package com.myproject.banknote.repo;

import com.myproject.banknote.entities.Banknote;
import com.myproject.banknote.entities.Currency;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BanknoteRepository extends JpaRepository<Banknote, UUID> {
    @Query("SELECT b FROM Banknote b WHERE b.currency = :currency")
    List<Banknote> findBanknoteByCurrency(@Param("currency") Currency currency);

    @Query("SELECT b FROM Banknote b WHERE b.id = :id")
    Banknote findBanknoteById(@Param("id") UUID id);

    @Query("SELECT b FROM Banknote b INNER JOIN Storage s ON b.id = s.banknote")
    List<Banknote> getBanknoteInStorage();
}