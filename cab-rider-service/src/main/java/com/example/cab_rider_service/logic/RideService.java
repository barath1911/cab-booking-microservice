package com.example.cab_rider_service.logic;

import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cab_rider_service.DTO.DriverResponseDTO;
import com.example.cab_rider_service.DTO.RideRequestDTO;
import com.example.cab_rider_service.DTO.RideResponseDTO;
import com.example.cab_rider_service.Enum.RideStatus;
import com.example.cab_rider_service.fegin.DriverClient;
import com.example.cab_rider_service.model.Ride;
import com.example.cab_rider_service.repository.RideRepository;


@Service
public class RideService {

	@Autowired
    private RideRepository rideRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private DriverClient driverClient;

    public double calculateFare(double distance){

    	double basefare=100;
          if(distance<=10) {
        	  return basefare;
          }

          double extraDistance=distance-10;

          double extrafare=(extraDistance/2)*20;

          return basefare+extrafare;
    }

    public double applySurge(double fare) {
    	int hour =LocalDateTime.now().getHour();

    	if((hour >= 8 && hour<= 11 )||(hour >=17 && hour <= 21)) {
    		return fare*1.5;
    	}
    	return fare;
    }
    // 📍 Haversine Formula (Distance Calculation)
    public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
    	double R=6371;
    	double dlat=Math.toRadians(lat2-lat1);
    	double dlon=Math.toRadians(lon2-lon1);

    	double a = Math.sin(dlat / 2)
    			* Math.sin(dlat / 2)
    	        + Math.cos(Math.toRadians(lat1))
    	        * Math.cos(Math.toRadians(lat2))
    	        * Math.sin(dlon / 2) * Math.sin(dlon / 2);

    	double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    	return R * c;
    }

    public void validateRide(RideRequestDTO request){
    	if(request.getPickupLat()==request.getDropLat() &&
    			request.getPickupLng() == request.getDropLng()) {
    		throw new RuntimeException("Pickup and Drop locations cannot be same");
    	}
    	if (request.getPickupLocation().trim().equalsIgnoreCase(request.getDropLocation().trim())) {
            throw new RuntimeException("Pickup and Drop locations cannot be same");
        }

    }


    public Ride updateRideStatus(Long rideId,RideStatus status) {
    	Ride ride = rideRepository.findById(rideId)
    			.orElseThrow(()-> new RuntimeException("Ride Not Found"));
    	ride.setStatus(status);
    	return rideRepository.save(ride);
    }


	public RideResponseDTO bookRide(RideRequestDTO request) {

		validateRide(request);

		//calculate distance//
		double distance = calculateDistance(
                request.getPickupLat(),
                request.getPickupLng(),
                request.getDropLat(),
                request.getDropLng()
        );
		// 💰 Calculate fare
        double fare = calculateFare(distance);

        fare = applySurge(fare);

		Ride ride=modelMapper.map(request, Ride.class);

		ride.setId(null);
		ride.setCreatedAt(LocalDateTime.now());
		ride.setDistance(distance);
		ride.setFare(fare);


		List<DriverResponseDTO> drivers = driverClient.getAvailableDrivers();

		if (drivers.isEmpty()){
	        ride.setStatus(RideStatus.NO_DRIVER_AVAILABLE);
	    } else {

	        // 👉 simple assign (first driver)
	        DriverResponseDTO driver = drivers.get(0);

	        ride.setDriverId(driver.getId());
	        ride.setDriverName(driver.getName());
	        ride.setStatus(RideStatus.ACCEPTED);

	        driverClient.updateDriverStatus(driver.getId(), "BUSY");
	    }
		 Ride savedRide = rideRepository.save(ride);

		 return modelMapper.map(savedRide, RideResponseDTO.class);
	}

	public Ride completeRide(Long rideId){

	    Ride ride = rideRepository.findById(rideId)
	            .orElseThrow(() -> new RuntimeException("Ride not found"));

	    ride.setStatus(RideStatus.COMPLETED);

	    // 🔥 driver free pannum
	    driverClient.updateDriverStatus(ride.getDriverId(), "AVAILABLE");

	    return rideRepository.save(ride);
	}

	public Ride getRideById(Long id){

	    return rideRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Ride not found"));
	}
}
