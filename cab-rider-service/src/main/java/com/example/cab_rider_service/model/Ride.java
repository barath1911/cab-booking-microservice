package com.example.cab_rider_service.model;

import java.time.LocalDateTime;

import com.example.cab_rider_service.Enum.RideStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="ride_info")
public class Ride {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long driverId;
    private String driverName;

    private String pickupLocation;
    private String dropLocation;

    private Double pickupLat;
    private Double pickupLng;

    private Double dropLat;
    private Double dropLng;

    private double distance;
    private double fare;

    @Enumerated(EnumType.STRING)
    private RideStatus status;
    private LocalDateTime createdAt;
}
