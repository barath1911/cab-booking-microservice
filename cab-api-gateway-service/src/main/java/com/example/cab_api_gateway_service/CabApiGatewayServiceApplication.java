package com.example.cab_api_gateway_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CabApiGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CabApiGatewayServiceApplication.class, args);
	}

}
