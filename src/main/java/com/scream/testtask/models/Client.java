package com.scream.testtask.models;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "client")
@Getter
@Setter
public class Client extends BaseEntity{
    @NotBlank(message = "Name is mandatory")
    @Column(name = "name")
    private String name;
    @Column(name = "number_phone")
    @NotBlank(message = "Number phone is mandatory")
    private String numberPhone;
    @NotBlank(message = "Email is mandatory")
    @Column(name = "email")
    private String email;
    @NotBlank(message = "Passport number is mandatory")
    @Column(name = "passport_number")
    private String passportNumber;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "bank_to_client",
            joinColumns = {@JoinColumn(name = "client_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "bank_id", referencedColumnName = "id")} )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Bank> banks;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<CreditOffer> creditOffer;

    @Override
    public String toString() {
        return name;
    }
}
