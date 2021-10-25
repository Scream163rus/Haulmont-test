package com.scream.testtask.repositories;

import com.scream.testtask.models.PlannedPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlannedPaymentRepository extends JpaRepository<PlannedPayment, String> {
    List<PlannedPayment> findAllByCreditOfferId(String id);
}
