package com.arquisoft.notification.microservice.dto;

public class NotificationFormat {
	private String contact;
	private String message;
	public String getContact() {
		return contact;
	}
	public String getMessage() {
		return message;
	}
	public NotificationFormat(String contact, String message) {
		super();
		this.contact = contact;
		this.message = message;
	}
	
}
