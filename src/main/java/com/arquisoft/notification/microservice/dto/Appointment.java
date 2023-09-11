package com.arquisoft.notification.microservice.dto;

import java.time.Instant;

public class Appointment {
	private Integer appointmentId;
	private Instant date;
	private Integer patientId;
	private String speciality;
	
	public Appointment(Integer appointmentId, Instant date, Integer patientId, String speciality) {
		super();
		this.appointmentId = appointmentId;
		this.date = date;
		this.patientId = patientId;
		this.speciality = speciality;
	}

	
	
	public String getSpeciality() {
		return speciality;
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
