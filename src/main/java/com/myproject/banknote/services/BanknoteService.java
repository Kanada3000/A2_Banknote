package com.myproject.banknote.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.myproject.banknote.entities.Banknote;
import com.myproject.banknote.repo.BanknoteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javassist.NotFoundException;

@Service
public class BanknoteService {
    private final BanknoteRepository banknoteRepository;

    @Autowired
    public BanknoteService(BanknoteRepository banknoteRepository) {
        this.banknoteRepository = banknoteRepository;
    }

    @Transactional(readOnly = true)
    public List<Banknote> getAllBanknote() {
        return banknoteRepository.getBanknoteInStorage();
    }

    @Transactional
    public void addBanknote(Banknote banknote) {
        if (banknoteRepository.findBanknoteById(banknote.getId()) == null) {
            banknoteRepository.save(banknote);
        }
    }

    @Transactional
    public List<Banknote> getBanknote() {
        return banknoteRepository.findAll();
    }

    @Transactional
    public Banknote getBanknoteById(UUID id) throws NotFoundException {
        Optional<Banknote> tempBanknote = banknoteRepository.findById(id);
        if (tempBanknote.isPresent())
            return tempBanknote.get();
        else
            throw new NotFoundException(String.format("Banknote with id %s wasn`t found", id));
    }

    @Transactional
    public void deleteBanknoteById(UUID id) throws NotFoundException {
        banknoteRepository.delete(getBanknoteById(id));
    }
}
