package com.scream.testtask.service;

import com.scream.testtask.models.CreditOffer;
import com.scream.testtask.repositories.CreditOfferRepository;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class CreditOfferService {
    private final CreditOfferRepository creditOfferRepository;
    public CreditOfferService(CreditOfferRepository creditOfferRepository){
        this.creditOfferRepository = creditOfferRepository;
    }

    @Transactional
    public CreditOffer get(@NotNull String id) {
        return creditOfferRepository.findById(id).orElseThrow();
    }

    @Transactional
    public List<CreditOffer> getAll() {
        return creditOfferRepository.findAll();
    }

    @Transactional
    public CreditOffer save(@NotNull CreditOffer creditOffer) {
        return creditOfferRepository.save(creditOffer);
    }
    @Transactional
    public void deleteById(@NotNull String id) {
    creditOfferRepository.deleteById(id);
    }
}
