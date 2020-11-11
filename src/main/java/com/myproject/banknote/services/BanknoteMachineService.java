package com.myproject.banknote.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.myproject.banknote.entities.Banknote;
import com.myproject.banknote.entities.Storage;
import com.myproject.banknote.entities.BanknoteMachine;
import com.myproject.banknote.entities.Currency;
import com.myproject.banknote.repo.BanknoteMachineRepository;
import com.myproject.banknote.repo.StorageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javassist.NotFoundException;

@Service
public class BanknoteMachineService {
    private final BanknoteMachineRepository banknoteMachineRepository;
    private final StorageRepository storageRepository;

    @Autowired
    public BanknoteMachineService(BanknoteMachineRepository banknoteMachineRepository,
                                  StorageRepository storageRepository) {
        this.banknoteMachineRepository = banknoteMachineRepository;
        this.storageRepository = storageRepository;
        System.out.println("--------------BanknoteMachineServiceCreate------------\n");
    }

    @Transactional
    public void addBanknoteToStorage(List<Banknote> banknoteToStorage, List<Integer> banknoteQuantities) {
        for (int i = 0; i < banknoteToStorage.size(); i++) {
            Banknote banknoteTemp = banknoteToStorage.get(i);
            int banknoteQTemp = banknoteQuantities.get(i);
            Storage existStorageBanknote = storageRepository.findByBanknote(banknoteTemp);
            if (existStorageBanknote == null) {
                Storage storageBanknote = new Storage();
                storageBanknote.setBanknote(banknoteTemp);
                storageBanknote.setQuantity(banknoteQTemp);
                storageBanknote.setId(UUID.randomUUID());
                storageRepository.save(storageBanknote);
            } else {
                int banknoteQuantity = existStorageBanknote.getQuantity();
                int newBanknoteQuantity = banknoteQuantity + banknoteQTemp;
                existStorageBanknote.setQuantity(newBanknoteQuantity);
                storageRepository.save(existStorageBanknote);
            }
        }
    }

    @Transactional
    public void addBanknoteMachine(BanknoteMachine banknoteMachine) {
        if ((banknoteMachineRepository.findBanknoteMachineByCurrency(banknoteMachine.getCurrency()) == null)
                || (banknoteMachineRepository.findBanknoteMachineBySpeed(banknoteMachine.getProductionSpeed()) == null)) {
            banknoteMachineRepository.save(banknoteMachine);
        }
    }

    @Transactional
    public List<BanknoteMachine> getBanknoteMachines() {
        return banknoteMachineRepository.findAll();
    }

    @Transactional
    public BanknoteMachine findBanknoteMachineByCurrency(Currency currency) {
        return banknoteMachineRepository.findBanknoteMachineByCurrency(currency);
    }

    @Transactional
    public BanknoteMachine getBanknoteMachineById(UUID id) throws NotFoundException {
        Optional<BanknoteMachine> tempBanknoteMachine = banknoteMachineRepository.findById(id);
        if (tempBanknoteMachine.isPresent())
            return tempBanknoteMachine.get();
        else
            throw new NotFoundException(String.format("Banknote machine with id %s was not found", id));
    }

    @Transactional
    public void deleteBanknoteMachineById(UUID id) throws NotFoundException {
        banknoteMachineRepository.delete(getBanknoteMachineById(id));
    }
}
