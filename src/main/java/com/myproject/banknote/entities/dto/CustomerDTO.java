package com.myproject.banknote.entities.dto;

public class CustomerDTO {
    private String name;
    private String surname;
    private String bank;

    public CustomerDTO() {
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

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getBank() {
        return bank;
    }
}
