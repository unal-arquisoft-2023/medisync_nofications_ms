package com.arquisoft.notification.microservice.controlller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arquisoft.notification.microservice.dto.Appointment;
import com.arquisoft.notification.microservice.dto.NotificationFormat;
import com.arquisoft.notification.microservice.model.AppointmentToNotify;
import com.arquisoft.notification.microservice.service.EmailService;
import com.arquisoft.notification.microservice.service.NotificationService;
import com.arquisoft.notification.microservice.service.SMSService;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private SMSService smsService;
	
	@Autowired
	private EmailService emailService;
	
	
	
	@PostMapping
	public AppointmentToNotify generateNotificationsForAppointment(@RequestBody Appointment appointment) throws IOException{
		return notificationService.createAppointmentToNotify(appointment);
	}
	
	@DeleteMapping("/{id}")
	public Integer deleteAppNotificationById(@PathVariable Integer id) {
		return notificationService.deleteById(id);
	}
	
	@PostMapping("/SMS")
	public String sendSMS(@RequestBody NotificationFormat body){
		return smsService.sendSMS(body.getContact(), body.getMessage());
	}
	
	@PostMapping("/EMAIL")
	public String sendEmail(@RequestBody NotificationFormat body) throws IOException{
		return emailService.sendEmail(body.getContact(), "Correo de prueba", body.getMessage());
	}
}
