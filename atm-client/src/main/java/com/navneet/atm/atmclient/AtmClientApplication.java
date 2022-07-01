package com.navneet.atm.atmclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class AtmClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtmClientApplication.class, args);
	}

}
