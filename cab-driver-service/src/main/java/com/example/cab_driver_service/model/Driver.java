package com.example.cab_driver_service.model;

import java.time.LocalDateTime;

import com.example.cab_driver_service.Enum.DriverStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="driver_info")
public class Driver{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String licenseNumber;

	@Column(unique = true)
	private String vehicleNumber;
	private String vehicleModel;

	private String licenseFilePath;

	@Enumerated(EnumType.STRING)
	private DriverStatus status;
	private LocalDateTime createdAt;
}
