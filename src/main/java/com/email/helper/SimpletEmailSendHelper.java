package com.email.helper;

import java.io.File;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class SimpletEmailSendHelper implements EmailSendHelper {

	private JavaMailSender mailSender;

	public SimpletEmailSendHelper(JavaMailSender mailSender) {
		super();
		this.mailSender = mailSender;
	}

	@Override
	public boolean sendEmail(String to, String subject, String message) {

		boolean isMailSent = false;
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(to);
			mailMessage.setSubject(subject);
			mailMessage.setText(message);

			mailSender.send(mailMessage);
			isMailSent = true;
		} catch (MailException e) {
			e.printStackTrace();
		}

		return isMailSent;
	}

	@Override
	public boolean sendEmailWithAttachment(String to, String subject, String message, String filePath) {

		boolean isMailSent = false;
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();

			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
			messageHelper.setTo(new InternetAddress(to));
			messageHelper.setFrom(new InternetAddress("learncodewithavi@gmail.com"));
			messageHelper.setSubject(subject);
			messageHelper.setText(message);

			FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));
			messageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);

			mailSender.send(mimeMessage);
			isMailSent = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isMailSent;
	}

	@Override
	public boolean sendEmailWithInlineResource(String to, String subject, String message, String filePath, String contentId) {

		boolean isMailSent = false;
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();

			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
			messageHelper.setTo(new InternetAddress(to));
			messageHelper.setFrom(new InternetAddress("learncodewithavi@gmail.com"));
			messageHelper.setSubject(subject);

			FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));

			//"<html><body><img src='cid:identifier1234'></body></html>"
			messageHelper.setText(message, true);

			//"identifier1234"
			messageHelper.addInline(contentId, fileSystemResource);

			mailSender.send(mimeMessage);
			isMailSent = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isMailSent;

	}

}
