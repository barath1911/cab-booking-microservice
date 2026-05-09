package com.example.cab_driver_service.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cab_driver_service.DTO.DriverRequestDTO;
import com.example.cab_driver_service.DTO.DriverResponseDTO;
import com.example.cab_driver_service.Enum.DriverStatus;
import com.example.cab_driver_service.logic.DriverService;
import com.example.cab_driver_service.model.Driver;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/drivers")
public class DriverController {

	@Autowired
	private DriverService driverService;

	@PostMapping("/register")
	public ResponseEntity<DriverResponseDTO> register(@Valid @ModelAttribute DriverRequestDTO requestDTO) throws IOException {
		DriverResponseDTO response= driverService.registerDriver(requestDTO);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}/status")
	public ResponseEntity<String> updateDriver(@PathVariable Long id,@RequestParam DriverStatus status) {
		 driverService.updateDriverStatus(id, status);
		 return ResponseEntity.ok("Driver status updated successfully");

	}

	@GetMapping("/available")
	public ResponseEntity<List<Driver>>getAvailableDrivers(){
		List<Driver> drivers = driverService.getAvailableDrivers();
		return ResponseEntity.ok(drivers);
	}
}
