package com.example.cab_payment_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.cab_payment_service.model.Payment;
import com.example.cab_payment_service.DTO.PaymentRequestDTO;
import com.example.cab_payment_service.logic.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping("/create")
	public ResponseEntity<Payment>createPayment(@Valid @RequestBody PaymentRequestDTO dto){

		Payment payment = paymentService.createPayment(dto);
		return ResponseEntity.ok(payment);
	}
}