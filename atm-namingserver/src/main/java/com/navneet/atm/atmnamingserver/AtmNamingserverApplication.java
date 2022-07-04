package com.navneet.atm.atmnamingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AtmNamingserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtmNamingserverApplication.class, args);
	}

}
