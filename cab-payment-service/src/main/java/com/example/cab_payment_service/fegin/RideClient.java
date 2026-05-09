package com.example.cab_payment_service.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.cab_payment_service.DTO.RideReponseDTO;

@FeignClient(name="ride-service",url="http://localhost:8083")
public interface RideClient{

	 @GetMapping("/rides/{rideId}")
	 RideReponseDTO getRideById(@PathVariable("rideId") Long rideId);
}
