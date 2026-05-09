package com.example.cab_payment_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cab_payment_service.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
