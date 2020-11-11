package com.myproject.banknote.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "banknote_machine")
public class BanknoteMachine {
    @Id
    @GeneratedValue
    private UUID id;
    @Column
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Column
    private int productionSpeed;

    public BanknoteMachine() {
    }

    public BanknoteMachine(Currency currency, int productionSpeed) {
        this.currency = currency;
        this.productionSpeed = productionSpeed;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setProductionSpeed(int productionSpeed) {
        this.productionSpeed = productionSpeed;
    }

    public UUID getId() {
        return id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public int getProductionSpeed() {
        return productionSpeed;
    }

    @Override
    public String toString() {
        return "Machine id = " + id + ", production speed = " + productionSpeed + " per second"
                + ", currency = " + currency;
    }
}
