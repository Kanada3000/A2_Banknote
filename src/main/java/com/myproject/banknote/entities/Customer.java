package com.myproject.banknote.entities;

import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    private UUID id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String bank;

    public Customer() {
    }

    public Customer(String name, String surname, String bank) {
        this.name = name;
        this.surname = surname;
        this.bank = bank;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getBank() {
        return bank;
    }

    @Override
    public String toString() {
        return "Customer id = " + id + ", name = " + name + ", surname = " + surname + ", bank = " + bank;
    }
}
