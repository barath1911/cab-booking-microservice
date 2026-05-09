package com.example.cab_payment_service.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentRequestDTO {

	    @NotNull(message = "Ride ID is required")
	    private Long rideId;

	    @NotNull(message = "User ID is required")
	    private Long userId;

	    @Min(value = 1, message = "Amount must be greater than 0")
	    private double amount;

	    @NotBlank(message = "Payment method is required")
	    private String paymentMethod;
}
