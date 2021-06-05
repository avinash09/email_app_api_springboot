package com.email.builder;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailSenderBuilder {

	private String to;

	private String subject;

	private String message;
	
	private boolean isAttachment;

	private JavaMailSender javaMailSender;

	private MimeMessage mimeMessage;

	private MimeMessageHelper messageHelper;
	
	private MailSender mailSender;

	public MailSenderBuilder(String to, String subject, String message, JavaMailSender mailSender, boolean isAttachment)
			throws MessagingException {
		super();
		this.mailSender= new SimpleMailSender(to, subject, message, isAttachment, mailSender);
	}

	public MailSenderBuilder addAttachment(String filePath) throws MessagingException {
		this.mailSender.addAttachment(filePath);
		return this;
	}

	public MailSenderBuilder addInline(String filePath, String contentId) throws MessagingException {
		this.mailSender.addInline(filePath, contentId);
		return this;
	}

	public MailSenderBuilder composeMessage() throws AddressException, MessagingException {
		this.mailSender.composeMessage();
		return this;
	}
	
	public MailSender build() {
		return this.mailSender;
	}

}
