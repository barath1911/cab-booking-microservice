package com.example.cab_driver_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CabDriverServiceApplication{

	public static void main(String[] args) {
		SpringApplication.run(CabDriverServiceApplication.class, args);
	}

}
