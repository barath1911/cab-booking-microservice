package com.example.cab_payment_service.logic;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cab_payment_service.DTO.PaymentRequestDTO;
import com.example.cab_payment_service.DTO.RideReponseDTO;
import com.example.cab_payment_service.Enum.PaymentMethod;
import com.example.cab_payment_service.Enum.PaymentStatus;
import com.example.cab_payment_service.fegin.RideClient;
import com.example.cab_payment_service.model.Payment;
import com.example.cab_payment_service.repository.PaymentRepository;

import jakarta.transaction.Transactional;

@Service
public class PaymentService {

@Autowired
private  PaymentRepository paymentRepository;

@Autowired
private RideClient rideClient;

@Autowired
private ModelMapper modelMapper;

@Transactional
public Payment createPayment(PaymentRequestDTO dto){
	 RideReponseDTO ride =
             rideClient.getRideById(dto.getRideId());

	 // Ride Status Check
     if (!ride.getStatus().equalsIgnoreCase("COMPLETED")) {
         throw new RuntimeException(
                 "Ride not completed. Payment not allowed"
         );
     }

     // Fare Check
     if (ride.getFare() != dto.getAmount()) {
         throw new RuntimeException(
                 "Payment amount mismatch"
         );

     }
   Payment payment = new Payment();
//
   payment.setRideId(dto.getRideId());
   payment.setUserId(dto.getUserId());
     payment.setAmount(dto.getAmount());



	payment.setPaymentMethod(
		    PaymentMethod.valueOf(
		        dto.getPaymentMethod().trim().toUpperCase()
		    )
		);

	 payment.setStatus(PaymentStatus.SUCCESS);

	 payment.setCreatedAt(LocalDateTime.now());

	 return paymentRepository.save(payment);

	}
}