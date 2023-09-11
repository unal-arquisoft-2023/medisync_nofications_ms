package com.arquisoft.notification.microservice.service;

import org.springframework.stereotype.Service;

import com.arquisoft.notification.microservice.dto.User;

@Service
public class UserService {
	public User getUserById(Integer id) {
		return new User("Kevin","example@email.com","+18651304023"); //TODO: 
	}

	public User getPatientInfoFromAppointment(Integer appointmentId) {
		return new User("Kevin","example@email.com","+18651304023");
	}
}
