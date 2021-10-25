package com.scream.testtask.service;

import com.scream.testtask.models.Credit;
import com.scream.testtask.repositories.CreditRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class CreditService{
    private final CreditRepository creditRepository;
    public CreditService(CreditRepository creditRepository){
        this.creditRepository = creditRepository;
    }
    @Transactional
    public Credit getCredit(@NotNull String id) {
        return creditRepository.findById(id).orElseThrow();
    }

    @Transactional
    public List<Credit> getAllCredits() {
        return creditRepository.findAll();
    }

    @Transactional
    public void saveCredit(@NotNull Credit credit) {
        creditRepository.save(credit);
    }

    @Transactional
    public void deleteCredit(@NotNull String id) {
        creditRepository.deleteById(id);
    }
}
