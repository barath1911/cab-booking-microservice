package com.example.cab_rider_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cab_rider_service.model.Ride;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {

}
