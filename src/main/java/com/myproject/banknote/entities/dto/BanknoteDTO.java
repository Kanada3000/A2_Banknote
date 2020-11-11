package com.myproject.banknote.entities.dto;

import java.util.List;

import com.myproject.banknote.entities.Banknote;

public class BanknoteDTO {

    private List<Banknote> banknote;

    public List<Banknote> getBanknote() {
        return banknote;
    }

    public void setBanknote(List<Banknote> banknote) {
        this.banknote = banknote;
    }

    @Override
    public String toString() {
        return "BanknoteDTO: " + "banknote = " + banknote;
    }

}
