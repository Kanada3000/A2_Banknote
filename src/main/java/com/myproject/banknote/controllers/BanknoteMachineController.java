package com.myproject.banknote.controllers;

import java.util.List;
import java.util.UUID;

import com.myproject.banknote.entities.Banknote;
import com.myproject.banknote.entities.BanknoteMachine;
import com.myproject.banknote.entities.dto.ServeDTO;
import com.myproject.banknote.services.BanknoteMachineService;
import com.myproject.banknote.services.BanknoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;

@RestController
@RequestMapping("banknoteMachine")
public class BanknoteMachineController {
    private final BanknoteMachineService banknoteMachineService;
    private final BanknoteService banknoteService;

    @Autowired
    public BanknoteMachineController(BanknoteMachineService banknoteMachineService, BanknoteService banknoteService) {
        this.banknoteMachineService = banknoteMachineService;
        this.banknoteService = banknoteService;
    }

    @GetMapping
    public ResponseEntity<List<BanknoteMachine>> show() {
        return ResponseEntity.ok(banknoteMachineService.getBanknoteMachines());
    }

    @GetMapping("{id}")
    public ResponseEntity<BanknoteMachine> showById(@PathVariable UUID id) throws NotFoundException {
        return ResponseEntity.ok(banknoteMachineService.getBanknoteMachineById(id));
    }

    @PostMapping
    public ResponseEntity<Void> getBanknoteFromMachine(@RequestBody ServeDTO serve) {
        List<Banknote> toStorage = serve.getBanknote();
        List<Integer> banknoteQuantity = serve.getBanknoteQuantity();
        BanknoteMachine banknoteMachine = serve.getBanknoteMachine();
        banknoteMachine.setId(UUID.randomUUID());
        for (Banknote b : toStorage) {
            b.setMadeBy(banknoteMachine);
            b.setId(UUID.randomUUID());
            banknoteService.addBanknote(b);
        }
        banknoteMachineService.addBanknoteToStorage(toStorage, banknoteQuantity);
        return ResponseEntity.ok().build();
    }
}
