package com.arquisoft.notification.microservice.model;

import java.time.Instant;
import java.util.List;

import com.arquisoft.notification.microservice.myenum.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="AppoinmentToNotify")
public class AppointmentToNotify {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable=false)
	private Integer appointmentId;
	
	@Column(nullable=false)//
	private Status status;
	
	@OneToMany(mappedBy="appointmentToNotify", cascade = CascadeType.ALL, orphanRemoval = true )
	private List<Notification> notifications;
	
	@Column(nullable=false )
	private Instant dateSchedule;
	
	

	public AppointmentToNotify(Integer appointmentId, Status status, List<Notification> notifcations,
			Instant dateSchedule) {
		super();
		this.appointmentId = appointmentId;
		this.status = status;
		this.notifications = notifcations;
		this.dateSchedule = dateSchedule;
	}

	public AppointmentToNotify() {
		
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Integer getId() {
		return id;
	}

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public Instant getDateSchedule() {
		return dateSchedule;
	}
	
}
