package com.arquisoft.notification.microservice.model;

import java.time.Instant;

import com.arquisoft.notification.microservice.myenum.Medium;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="notification")
public class Notification {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;
	
	@Column(name="message", nullable=false)
	private String message;
	
	@Column(nullable=false, columnDefinition = "timestamp(6) with time zone default now()")
	private Instant date;
	
	@Column(nullable=false)
	private Medium type;
	
	private boolean seen;
	
	@ManyToOne()
	@JoinColumn(name="Appointment_To_Notify_Id")
	private AppointmentToNotify appointmentToNotify;

	public Notification( String message, Instant date, Medium type, boolean seen,AppointmentToNotify appointmentToNotify) {
		super();
		this.message = message;
		this.date = date;
		this.type = type;
		this.seen = seen;
		this.appointmentToNotify = appointmentToNotify;
	}
	
	
	
	public AppointmentToNotify getAppointmentToNotify() {
		return appointmentToNotify;
	}



	public void setAppointmentToNotify(AppointmentToNotify appointmentToNotify) {
		this.appointmentToNotify = appointmentToNotify;
	}



	public Notification() {
		
	}

	public Medium getType() {
		return type;
	}

	public void setType(Medium type) {
		this.type = type;
	}


	public int getId() {
		return id;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}
	
	
}
