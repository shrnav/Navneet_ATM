package com.navneet.atm.atmgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AtmGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtmGatewayApplication.class, args);
	}

}
