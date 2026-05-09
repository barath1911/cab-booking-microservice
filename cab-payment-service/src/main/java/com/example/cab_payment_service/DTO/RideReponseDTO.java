package com.example.cab_payment_service.DTO;

import lombok.Data;

@Data
public class RideReponseDTO {

	private Long rideId;

    private double fare;

    private String status;
}
