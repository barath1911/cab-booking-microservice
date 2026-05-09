package com.example.cab_rider_service.DTO;

import com.example.cab_rider_service.Enum.RideStatus;

import lombok.Data;

@Data
public class RideResponseDTO {

	private Long id;
    private Long driverId;
    private String driverName;

    private String pickupLocation;
    private String dropLocation;

    private double distance;
    private double fare;
    private RideStatus status;

}
