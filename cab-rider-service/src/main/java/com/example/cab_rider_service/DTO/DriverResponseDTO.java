package com.example.cab_rider_service.DTO;

import lombok.Data;

@Data
public class DriverResponseDTO {

	private Long id;
	private String name;
	private String phone;
	private String vehicleNumber;
	private String vehicleModel;
	private String status;
}
