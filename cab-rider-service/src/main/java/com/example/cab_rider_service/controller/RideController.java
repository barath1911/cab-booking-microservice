package com.example.cab_rider_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cab_rider_service.DTO.RideRequestDTO;
import com.example.cab_rider_service.DTO.RideResponseDTO;
import com.example.cab_rider_service.Enum.RideStatus;
import com.example.cab_rider_service.logic.RideService;
import com.example.cab_rider_service.model.Ride;
import com.example.cab_rider_service.repository.RideRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rides")
public class RideController {

	@Autowired
	private RideService rideService;

	@Autowired
	private RideRepository rideRepository;


	@PostMapping("/book")
	public ResponseEntity<RideResponseDTO> bookRide(@RequestBody RideRequestDTO request){
	    return ResponseEntity.ok(rideService.bookRide(request));
	}

	 @PutMapping("/{rideId}/status")
	public ResponseEntity<Ride> updateRideStatus(
            @PathVariable Long rideId,
            @RequestParam RideStatus status) {

        Ride updatedRide = rideService.updateRideStatus(rideId, status);


        return ResponseEntity.ok(updatedRide);
    }
	 @GetMapping("/{id}")
	    public RideResponseDTO getRideById(
	            @PathVariable Long id
	    ) {

	        Ride ride = rideRepository.findById(id)
	                .orElseThrow(() ->
	                        new RuntimeException("Ride not found"));

	        RideResponseDTO dto = new RideResponseDTO();

	        dto.setId(ride.getId());
	        dto.setFare(ride.getFare());
	        dto.setStatus(ride.getStatus());


	        return dto;
	 }
	}
