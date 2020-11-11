package com.myproject.banknote.entities;

import java.util.List;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "order_banknote", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "banknote_id"))
    private List<Banknote> orderBanknote;

    public Order() {
    }

    public Order(List<Banknote> orderBanknote, Customer customer) {
        this.customer = customer;
        this.orderBanknote = orderBanknote;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setBanknote(List<Banknote> orderBanknote) {
        this.orderBanknote = orderBanknote;
    }

    public UUID getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Banknote> getBanknote() {
        return orderBanknote;
    }

    @Override
    public String toString() {
        return "Order id = " + id + ", banknote = " + orderBanknote + ", customer = "
                + customer;
    }
}
