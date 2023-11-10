package com.arquisoft.notification.microservice.dto;

import java.time.Instant;

public class Appointment {
	private Integer appointmentId;
	private Instant date;
	private Integer patientId;
	private String specialty;
	
	public Appointment(Integer appointmentId, Instant date, Integer patientId, String specialty) {
		super();
		this.appointmentId = appointmentId;
		this.date = date;
		this.patientId = patientId;
		this.specialty = specialty;
	}

	
	
	public String getspecialty() {
		return specialty;
	}



	public Integer getAppointmentId() {
		return appointmentId;
	}

	public Instant getDate() {
		return date;
	}

	public Integer getPatientId() {
		return patientId;
	}
	
	
	
}
