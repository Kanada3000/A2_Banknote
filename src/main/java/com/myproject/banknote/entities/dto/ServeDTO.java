package com.myproject.banknote.entities.dto;

import java.util.List;

import com.myproject.banknote.entities.Banknote;
import com.myproject.banknote.entities.BanknoteMachine;

public class ServeDTO {
    private List<Banknote> banknote;
    private List<Integer> banknoteQuantity;
    private BanknoteMachine banknoteMachine;

    public ServeDTO() {
    }

    public ServeDTO(List<Banknote> banknote, List<Integer> banknoteQuantity, BanknoteMachine banknoteMachine) {
        this.banknote = banknote;
        this.banknoteQuantity = banknoteQuantity;
        this.banknoteMachine = banknoteMachine;
    }

    public void setBanknote(List<Banknote> banknote) {
        this.banknote = banknote;
    }

    public void setBanknoteQuantity(List<Integer> banknoteQuantity) {
        this.banknoteQuantity = banknoteQuantity;
    }

    public void setBanknoteMachine(BanknoteMachine banknoteMachine) {
        this.banknoteMachine = banknoteMachine;
    }

    public List<Banknote> getBanknote() {
        return banknote;
    }

    public List<Integer> getBanknoteQuantity() {
        return banknoteQuantity;
    }

    public BanknoteMachine getBanknoteMachine() {
        return banknoteMachine;
    }

    @Override
    public String toString() {
        return "ServeDTO banknote = " + banknote + ", banknoteQuantity = " + banknoteQuantity + ", banknote machine = "
                + banknoteMachine;
    }
}
