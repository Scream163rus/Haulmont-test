package com.scream.testtask.models;

import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@Table(name = "credit")
public class Credit extends BaseEntity {
    @Column(name = "credit_limit")
    @NotNull(message = "Credit limit is mandatory")
    private BigDecimal creditLimit;
    @Column(name = "percent_rate")
    @NotNull(message = "Percent rate is mandatory")
    private BigDecimal percentRate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    @EqualsAndHashCode.Exclude
    @NotNull(message = "Bank is mandatory")
    private Bank bank;
    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private List<CreditOffer> creditOffers;

    @Override
    public String toString() {
        return "(" + creditLimit + "," + percentRate + ")";
    }
}
