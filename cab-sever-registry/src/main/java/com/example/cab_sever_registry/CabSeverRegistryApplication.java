package com.example.cab_sever_registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CabSeverRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CabSeverRegistryApplication.class, args);
	}

}
