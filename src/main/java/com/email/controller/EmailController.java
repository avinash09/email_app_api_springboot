package com.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.email.module.EmailRequest;
import com.email.module.EmailResponse;
import com.email.services.EmailService;

@RestController
@CrossOrigin
public class EmailController {

	@Autowired
	private EmailService emailService;

	@RequestMapping(value = "/sendemail", method = RequestMethod.POST)
	public ResponseEntity<EmailResponse> sendEmail(@RequestBody EmailRequest emailRequest) {

		System.out.println(emailRequest.toString());

		boolean isMailSent = emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(),
				emailRequest.getMessage());

		if (isMailSent) {
			return ResponseEntity.ok().body(new EmailResponse("Email Send Successfully"));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Email Send Fail"));
		}
	}
}
