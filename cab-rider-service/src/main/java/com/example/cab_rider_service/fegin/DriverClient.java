package com.example.cab_rider_service.fegin;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cab_rider_service.DTO.DriverResponseDTO;

@FeignClient(name="driver-service" ,url="http://localhost:8082")
public interface DriverClient {

	 @GetMapping("/drivers/available")
	    List<DriverResponseDTO> getAvailableDrivers();

	 @PutMapping("/drivers/{id}/status")
	 void updateDriverStatus(@PathVariable Long id,
	                         @RequestParam String status);
}
