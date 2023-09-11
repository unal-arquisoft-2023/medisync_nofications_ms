package com.arquisoft.notification.microservice.service;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.arquisoft.notification.microservice.db.AppointmentToNotifyRepository;
import com.arquisoft.notification.microservice.db.NotificationRepository;
import com.arquisoft.notification.microservice.dto.Appointment;
import com.arquisoft.notification.microservice.dto.User;
import com.arquisoft.notification.microservice.model.AppointmentToNotify;
import com.arquisoft.notification.microservice.model.Notification;
import com.arquisoft.notification.microservice.myenum.Medium;
import com.arquisoft.notification.microservice.myenum.Status;

@Service
public class NotificationService {
	
	private static Logger logger = LoggerFactory.getLogger(NotificationService.class);
	
	private String subject="Recordatorio de cita medica";
	private String message="""
			Estimado %s, se le recuerda que tiene cita medica, el dia %s a las %s le agradecemos llegar con 20 minutos de anticipacion al centro medico.
			Muchas gracias.
			"""; //TODO: Add especialization

	@Autowired
	private UserService userService;
	
	@Autowired
	private SMSService smsService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private NotificationRepository notificationRepo;
	
	@Autowired
	private AppointmentToNotifyRepository appointmentToNotRepo;
	
	public AppointmentToNotify createAppointmentToNotify(Appointment appointment) {
		AppointmentToNotify appointmentToNotify = appointmentToNotRepo.save(new AppointmentToNotify(appointment.getAppointmentId(),
				Status.UNNOTIFIED,Collections.<Notification>emptyList(),appointment.getDate()));
		return  appointmentToNotify;
	}

	public Integer deleteById(Integer id) {
		logger.info("Delete "+id);
		appointmentToNotRepo.deleteById(id);
		return id;
	}
	@Scheduled(cron = "0 0 0 */1 * *")
	public void generateNotificationsEachDay() throws IOException {
		logger.info("Execution cron per day");
		List<AppointmentToNotify> unnotifiedAppointments = appointmentToNotRepo.findByStatus(Status.UNNOTIFIED);
		for (AppointmentToNotify a:unnotifiedAppointments) {
			Instant actual = Instant.now();
			Instant actualPlusTwo = actual.plus(2,ChronoUnit.DAYS);
			//Instant actualMinusTwo = actual.minus(2,ChronoUnit.DAYS);
			Instant schedule = a.getDateSchedule();
			if(schedule.compareTo(actual)>=0 && schedule.compareTo(actualPlusTwo)<=0) {
				User userinfo = userService.getPatientInfoFromAppointment(a.getAppointmentId());
				String newMessage = String.format(message, userinfo.getName(), 
						LocalDate.ofInstant(a.getDateSchedule(),ZoneOffset.of("-05:00")).toString(),
						LocalTime.ofInstant(a.getDateSchedule(),ZoneOffset.of("-05:00")).toString());
				smsService.sendSMS(userinfo.getPhone(), newMessage);
				logger.info("SMS Sent");
				Notification notification = new Notification(newMessage,Instant.now(),Medium.SMS,false,a);
				notificationRepo.save(notification);
				emailService.sendEmail(userinfo.getEmail(), subject, newMessage);
				logger.info("Email Sent");
				Notification notification1 = new Notification(newMessage,Instant.now(),Medium.GMAIL,false,a);
				notificationRepo.save(notification1);
				a.setStatus(Status.PARTIAL);
				appointmentToNotRepo.save(a);
			}
		}
	}
	@Scheduled(cron = "0 0 */1 * * *")
	public void generateNotificationsEachHour() throws IOException {
		logger.info("Execution cron per hour");
		List<AppointmentToNotify> unnotifiedAppointments = appointmentToNotRepo.findByStatus(Status.PARTIAL);
		for (AppointmentToNotify a:unnotifiedAppointments) {
			Instant actual = Instant.now();
			Instant actualPlusTwo = actual.plus(3,ChronoUnit.HOURS);
			//Instant actualMinusTwo = actual.minus(2,ChronoUnit.DAYS);
			Instant schedule = a.getDateSchedule();
			if(schedule.compareTo(actual)>=0 && schedule.compareTo(actualPlusTwo)<=0) {
				User userinfo = userService.getPatientInfoFromAppointment(a.getAppointmentId());
				String newMessage = String.format(message, userinfo.getName(), 
						LocalDate.ofInstant(a.getDateSchedule(),ZoneOffset.of("-05:00")).toString(),
						LocalTime.ofInstant(a.getDateSchedule(),ZoneOffset.of("-05:00")).toString());
				smsService.sendSMS(userinfo.getPhone(), newMessage);
				logger.info("SMS Sent");
				Notification notification = new Notification(newMessage,Instant.now(),Medium.SMS,false,a);
				notificationRepo.save(notification);
				emailService.sendEmail(userinfo.getEmail(), subject, newMessage);
				logger.info("Email Sent");
				Notification notification1 = new Notification(newMessage,Instant.now(),Medium.GMAIL,false,a);
				notificationRepo.save(notification1);
				a.setStatus(Status.NOTIFIED);
				appointmentToNotRepo.save(a);
			}
		}
	}
}
