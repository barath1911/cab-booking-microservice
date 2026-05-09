 package com.example.cab_payment_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CabPaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CabPaymentServiceApplication.class, args);
	}

}
