package com.arquisoft.notification.microservice.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arquisoft.notification.microservice.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

}
