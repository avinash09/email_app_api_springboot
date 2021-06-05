package com.email.builder;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface MailSender {

	public void addAttachment(String filePath) throws MessagingException;
	
	public void addInline(String filePath, String contentId) throws MessagingException;
	
	public void composeMessage() throws AddressException, MessagingException;
	
	public boolean sendEmail();
}
