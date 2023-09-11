package com.arquisoft.notification.microservice.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arquisoft.notification.microservice.model.AppointmentToNotify;
import com.arquisoft.notification.microservice.myenum.Status;

public interface AppointmentToNotifyRepository extends JpaRepository<AppointmentToNotify,Integer> {
	List<AppointmentToNotify> findByStatus(Status status);
}
