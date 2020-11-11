package com.myproject.banknote.entities;

import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name = "storage")
public class Storage {
    @Id
    @GeneratedValue
    private UUID id;
    @Column
    private int quantity;

    @OneToOne
    private Banknote banknote;

    public Storage() {
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setBanknote(Banknote banknote) {
        this.banknote = banknote;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public UUID getId() {
        return id;
    }

    public Banknote getBanknote() {
        return banknote;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Storage id = " + id;
    }
}