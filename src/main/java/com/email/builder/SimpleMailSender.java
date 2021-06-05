package com.email.builder;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SimpleMailSender implements MailSender {

	private String to;

	private String subject;

	private String message;

	private boolean isAttachment;

	private JavaMailSender mailSender;

	private MimeMessage mimeMessage;

	private MimeMessageHelper messageHelper;

	public SimpleMailSender(String to, String subject, String message, boolean isAttachment, JavaMailSender mailSender)
			throws MessagingException {
		super();
		this.to = to;
		this.subject = subject;
		this.message = message;
		this.isAttachment = isAttachment;
		this.mailSender = mailSender;
		this.mimeMessage = mailSender.createMimeMessage();
		this.messageHelper = new MimeMessageHelper(mimeMessage, this.isAttachment);
	}

	@Override
	public void addAttachment(String filePath) throws MessagingException {
		FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));
		messageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
	}

	@Override
	public void addInline(String filePath, String contentId) throws MessagingException {
		FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));

		// "<html><body><img src='cid:identifier1234'></body></html>"
		messageHelper.setText(message, true);

		// "identifier1234"
		messageHelper.addInline(contentId, fileSystemResource);
	}

	@Override
	public void composeMessage() throws AddressException, MessagingException {
		this.messageHelper.setTo(new InternetAddress(to));
		this.messageHelper.setFrom(new InternetAddress("learncodewithavi@gmail.com"));
		this.messageHelper.setSubject(subject);
		this.messageHelper.setText(this.message);
	}

	@Override
	public boolean sendEmail() {

		boolean isMailSent = false;
		try {
			mailSender.send(this.mimeMessage);
			isMailSent = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isMailSent;

	}

}
