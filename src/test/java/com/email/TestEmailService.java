package com.email;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.email.services.EmailService;

public class TestEmailService {

	@Test
	public void testsendEmail() {
		String to ="avinashjbs@gmail.com";
		String subject = "spring boot test";
		String message = "test";
		
		assertTrue(new EmailService().sendEmail(to, subject, message));
		
		}

}
