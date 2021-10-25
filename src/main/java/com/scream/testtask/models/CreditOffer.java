package com.scream.testtask.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "CreditOffer")
public class CreditOffer extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @NotNull(message = "Client is mandatory")
    private Client client;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @NotNull(message = "Credit is mandatory")
    private Credit credit;
    @Column(name = "sum_credit")
    @NotNull(message = "Sum credit is mandatory")
    private BigDecimal sumCredit;
    @OrderBy("datePayment ASC")
    @OneToMany(mappedBy = "creditOffer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<PlannedPayment> paymentSchedule;

}
