package com.scream.testtask.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@Table(name = "planned_payment")
public class PlannedPayment extends BaseEntity {
    @Column(name = "date_payment")
    private LocalDate datePayment;
    @Column(name = "sum_payment")
    private BigDecimal sumPayment;
    @Column(name = "credit_body_amount")
    private BigDecimal creditBodyAmount;
    @Column(name = "credit_percent_amount")
    private BigDecimal creditPercentAmount;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credit_offer_id")
    private CreditOffer creditOffer;

}
