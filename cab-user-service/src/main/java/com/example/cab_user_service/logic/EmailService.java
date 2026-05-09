package com.example.cab_user_service.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendWelcomeEmail(String toEmail, String name){
		 SimpleMailMessage message = new SimpleMailMessage();

		 message.setTo(toEmail);
		 message.setSubject("Welcome to Cab Booking App");
		 message.setText("Hello " + name + ", your account created successfully!");
		 javaMailSender.send(message);
	}
}
