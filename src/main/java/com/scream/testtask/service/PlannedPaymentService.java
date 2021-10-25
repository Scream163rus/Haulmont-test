package com.scream.testtask.service;

import com.scream.testtask.models.PlannedPayment;
import com.scream.testtask.repositories.PlannedPaymentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Service
public class PlannedPaymentService {
    private final PlannedPaymentRepository plannedPaymentRepository;

    public PlannedPaymentService(PlannedPaymentRepository plannedPaymentRepository) {
        this.plannedPaymentRepository = plannedPaymentRepository;
    }

    @Transactional
    public BigDecimal sumPercentCredit(@NotNull String id) {
        return plannedPaymentRepository.findAllByCreditOfferId(id).stream().map((s) -> s.getCreditPercentAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Transactional
    public void save(@NotNull PlannedPayment plannedPayment) {
        plannedPaymentRepository.save(plannedPayment);
    }
}

