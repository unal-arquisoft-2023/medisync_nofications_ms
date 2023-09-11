package com.arquisoft.notification.microservice.dto;

public class User {
	private String name;
	private String email;
	private String phone;
	
	public User(String name, String email, String phone) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}
	
	
}
