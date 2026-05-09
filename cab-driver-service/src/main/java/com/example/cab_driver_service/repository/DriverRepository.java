package com.example.cab_driver_service.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cab_driver_service.Enum.DriverStatus;
import com.example.cab_driver_service.model.Driver;


@Repository
public interface DriverRepository extends JpaRepository<Driver, Long>{

	boolean existsByEmail(String email);
	boolean existsByVehicleNumber(String vehicleNumber);
	Optional<Driver> findByEmail(String email);
	List<Driver> findByStatus(DriverStatus status);

}
