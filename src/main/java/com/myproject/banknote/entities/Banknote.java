package com.myproject.banknote.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name = "banknote")
public class Banknote {
    @Id
    private UUID id;
    @Column
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    private BanknoteMachine madeBy;

    public Banknote() {
    }

    public Banknote(Currency currency, BanknoteMachine madeBy) {
        this.currency = currency;
        this.madeBy = madeBy;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setMadeBy(BanknoteMachine madeBy) {
        this.madeBy = madeBy;
    }

    public UUID getId() {
        return id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BanknoteMachine getMadeBy() {
        return madeBy;
    }

    @Override
    public String toString() {
        return "Banknote id = " + id + ", type = " + currency + ", madeBy = " + madeBy;
    }
}
