package com.email.helper;

public interface EmailSendHelper {
	
	public boolean sendEmail(String to, String subject, String message);
	
	public boolean sendEmailWithAttachment(String to, String subject, String message, String filePath);
	
	public boolean sendEmailWithInlineResource(String to, String subject, String message, String filePath, String contentId);

}
