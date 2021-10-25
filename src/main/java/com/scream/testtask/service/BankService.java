package com.scream.testtask.service;

import com.scream.testtask.models.Bank;
import com.scream.testtask.repositories.BankRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class BankService {
    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Transactional
    public Bank getBank(@NotNull String id) {
        return bankRepository.findById(id).orElseThrow();
    }

    @Transactional
    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    @Transactional
    public void saveBank(@NotNull Bank bank) {
        bankRepository.save(bank);
    }

    @Transactional
    public void deleteBank(@NotNull String id) {
        bankRepository.deleteById(id);
    }
}
