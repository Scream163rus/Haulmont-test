package com.scream.testtask.repositories;

import com.scream.testtask.models.CreditOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CreditOfferRepository extends JpaRepository<CreditOffer, String> {
}
