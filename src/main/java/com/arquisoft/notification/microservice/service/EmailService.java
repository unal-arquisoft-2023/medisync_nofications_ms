package com.arquisoft.notification.microservice.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service

public class EmailService {

	private static Logger logger = LoggerFactory.getLogger(EmailService.class);
	
	@Value("${SENGRID_API_KEY}")
	private String API_KEY;


	public String sendEmail(String emailTo, String subject, String message) throws IOException {
		Email to = new Email(emailTo);
		Content content = new Content("text/plain", message);
		Mail mail = new Mail(new Email("medicalnotifyservice@gmail.com"), subject, to, content);

		SendGrid sg = new SendGrid(API_KEY);
		Request request = new Request();

		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			
			logger.info("Sending email notification");
			logger.info("Status "+response.getStatusCode());
			logger.info("Body "+response.getBody());
			logger.info("Headers "+response.getHeaders());
			
		} catch (IOException ex) {
			throw ex;
		}

		return "";
	}
}
