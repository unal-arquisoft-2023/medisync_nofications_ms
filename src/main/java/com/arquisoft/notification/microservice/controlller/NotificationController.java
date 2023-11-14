package com.arquisoft.notification.microservice.controlller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arquisoft.notification.microservice.dto.NotificationFormat;
import com.arquisoft.notification.microservice.service.EmailService;
import com.arquisoft.notification.microservice.service.SMSService;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
	
	
	@Autowired
	private SMSService smsService;
	
	@Autowired
	private EmailService emailService;
	
	
	@PostMapping("/SMS")
	public String sendSMS(@RequestBody NotificationFormat body){
		return smsService.sendSMS(body.getContact(), body.getMessage());
	}
	
	@PostMapping("/EMAIL")
	public String sendEmail(@RequestBody NotificationFormat body) throws IOException{
		return emailService.sendEmail(body.getContact(), "Notificacion de cita", body.getMessage());
	}
}
