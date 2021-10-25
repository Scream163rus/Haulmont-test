package com.scream.testtask.service;

import com.scream.testtask.models.CreditOffer;
import com.scream.testtask.models.PlannedPayment;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;


@Service
public class ProcessingCreditService {
    private final CreditOfferService creditOfferService;
    private final PlannedPaymentService plannedPaymentService;
    private final BigDecimal ONE_HUNDRED_PERCENT = BigDecimal.valueOf(100);
    private final BigDecimal DAYS_IN_MONTH = BigDecimal.valueOf(12);
    private final BigDecimal ONE = BigDecimal.valueOf(1);
    public ProcessingCreditService(CreditOfferService creditOfferService, PlannedPaymentService plannedPaymentService){
        this.creditOfferService = creditOfferService;
        this.plannedPaymentService = plannedPaymentService;
    }
    @Transactional
    public void creditPayment(@NotNull String id, int month){
        CreditOffer creditOffer = creditOfferService.get(id);
        BigDecimal percentRatePerMonth = creditOffer.getCredit().getPercentRate().divide(ONE_HUNDRED_PERCENT).divide(DAYS_IN_MONTH, 4 , RoundingMode.HALF_UP);
        BigDecimal sumCredit = creditOffer.getSumCredit();
        BigDecimal sumMonthlyPayment = sumCredit.multiply(percentRatePerMonth.add(percentRatePerMonth
                .divide(percentRatePerMonth.add(ONE).pow(month).subtract(ONE), 15 , RoundingMode.HALF_UP)));
        int numberPayment = 0;
        while (numberPayment < month){
            PlannedPayment plannedPayment = new PlannedPayment();
            plannedPayment.setCreditOffer(creditOffer);
            plannedPayment.setDatePayment(LocalDate.now().plusMonths(numberPayment));
            plannedPayment.setSumPayment(sumMonthlyPayment);
            plannedPayment.setCreditPercentAmount(sumCredit.multiply(percentRatePerMonth));
            plannedPayment.setCreditBodyAmount(sumMonthlyPayment.subtract(sumCredit.multiply(percentRatePerMonth)));
            plannedPaymentService.save(plannedPayment);
            sumCredit = sumCredit.subtract(sumMonthlyPayment.subtract(sumCredit.multiply(percentRatePerMonth)));
            numberPayment++;
        }

    }
}
