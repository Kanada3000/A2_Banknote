package com.myproject.banknote.config;

import com.myproject.banknote.entities.*;
import com.myproject.banknote.repo.BanknoteMachineRepository;
import com.myproject.banknote.repo.BanknoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Configuration
public class appConfig {
    @Bean
    public CommandLineRunner demo(final BanknoteRepository banknoteRepository, final BanknoteMachineRepository banknoteMachineRepository) {
        return strings -> {
//            List<Banknote> orderedList = new ArrayList<Banknote>();
            BanknoteMachine banknoteMachine = new BanknoteMachine(Currency.UAH, 170);
            Banknote banknote = new Banknote(Currency.UAH, banknoteMachine);
            banknote.setId(UUID.randomUUID());
            banknoteRepository.save(banknote);
            banknoteMachine.setId(UUID.randomUUID());
//            banknoteMachine.addBanknoteToList(banknote);
//            banknoteMachineRepository.save(banknoteMachine);
        };
    }
}
