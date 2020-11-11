package com.myproject.banknote.entities.dto;

import java.util.List;

import com.myproject.banknote.entities.Banknote;
import com.myproject.banknote.entities.Customer;

public class NewOrderDTO {
    private Customer customer;
    private List<Banknote> banknote;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setBanknote(List<Banknote> banknote) {
        this.banknote = banknote;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Banknote> getBanknote() {
        return banknote;
    }
}
