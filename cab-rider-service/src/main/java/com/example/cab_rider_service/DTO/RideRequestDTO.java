package com.example.cab_rider_service.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RideRequestDTO {

	    @NotNull(message = "Pickup latitude required")
	    private Double pickupLat;

	    @NotNull(message = "Pickup longitude required")
	    private Double pickupLng;

	    @NotNull(message = "Drop latitude required")
	    private Double dropLat;

	    @NotNull(message = "Drop longitude required")
	    private Double dropLng;

	    @NotBlank(message = "Pickup location is required")
	    private String pickupLocation;

	    @NotBlank(message = "Drop location is required")
	    private String dropLocation;
}
