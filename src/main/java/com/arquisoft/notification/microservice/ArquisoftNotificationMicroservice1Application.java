package com.arquisoft.notification.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ArquisoftNotificationMicroservice1Application {
	
	
	public static void main(String[] args) {
		SpringApplication.run(ArquisoftNotificationMicroservice1Application.class, args);
	}

}
