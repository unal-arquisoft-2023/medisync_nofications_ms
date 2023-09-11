package com.arquisoft.notification.microservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import jakarta.annotation.PostConstruct;

@Service
public class SMSService {
	
	private static Logger logger = LoggerFactory.getLogger(SMSService.class);
	
	@Value("${TWILIO_ACCOUNT_SID}")
	private String ACCOUNT_SID;
	
	@Value("${TWILIO_AUTH_TOKEN}")
	private String AUTH_TOKEN;
	
	@Value("${TWILIO_SMS_OUTGOING_NUMBER}")
	private String SMS_OUTGOING_NUMBER;
	
	@PostConstruct
	public void setup() {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		logger.info("Account SID "+ACCOUNT_SID);
		//System.out.println("Account SID "+ACCOUNT_SID);
	}
	
	public String sendSMS(String smsNumber,String smsMessage) {
		logger.info("Sending notification via SMS");
		Message message = Message.creator(
				new PhoneNumber(smsNumber), 
				new PhoneNumber(SMS_OUTGOING_NUMBER), 
				smsMessage).create();
		return message.getStatus().toString();
	}
}
