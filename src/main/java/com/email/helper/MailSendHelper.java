package com.email.helper;

import java.io.File;
import java.io.IOException;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import com.email.factory.EmaiSendHelperFactory;

public class MailSendHelper implements EmailSendHelper {

	private Message mailmessage;

	public MailSendHelper(Message mailmessage) {
		super();
		this.mailmessage = mailmessage;
	}

	@Override
	public boolean sendEmail(String to, String subject, String message) {
		boolean isMailSent = false;

		try {

			// step 2: compose the message [test, multi media]
			Message mailmessage = composeMessage(to, subject, message);

			// step 3: send message using Transport class
			Transport.send(mailmessage);

			System.out.println("mail send sucessfully");
			isMailSent = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isMailSent;
	}

	private Message composeMessage(String to, String subject, String message) throws MessagingException {

		// set to
		this.mailmessage.setRecipient(Message.RecipientType.TO, EmaiSendHelperFactory.getToAddress(to));

		// set subject
		this.mailmessage.setSubject(subject);

		// set message
		this.mailmessage.setText(message);

		return this.mailmessage;
	}

	@Override
	public boolean sendEmailWithAttachment(String to, String subject, String message, String filePath) {

		boolean isMailSent = false;

		try {

			// step 2: compose the message [test, multi media]
			Message mailmessage = composeMessage(to, subject, message, filePath);

			// step 3: send message using Transport class
			Transport.send(mailmessage);

			System.out.println("mail send sucessfully");
			isMailSent = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isMailSent;

	}

	private Message composeMessage(String to, String subject, String message, String filePath)
			throws MessagingException, IOException {

		// set to
		this.mailmessage.setRecipient(Message.RecipientType.TO, EmaiSendHelperFactory.getToAddress(to));

		// set subject
		this.mailmessage.setSubject(subject);

		MimeMultipart mimeMultipart = new MimeMultipart();

		MimeBodyPart textBodyPart = new MimeBodyPart();

		MimeBodyPart fileBodyPart = new MimeBodyPart();

		textBodyPart.setText(message);

		fileBodyPart.attachFile(new File(filePath));

		mimeMultipart.addBodyPart(textBodyPart);
		mimeMultipart.addBodyPart(fileBodyPart);

		this.mailmessage.setContent(mimeMultipart);

		return this.mailmessage;
	}

	@Override
	public boolean sendEmailWithInlineResource(String to, String subject, String message, String filePath, String contentId) {
		boolean isMailSent = false;

		try {

			// set to
			this.mailmessage.setRecipient(Message.RecipientType.TO, EmaiSendHelperFactory.getToAddress(to));

			// set subject
			this.mailmessage.setSubject(subject);

			MimeMultipart mimeMultipart = new MimeMultipart();

			MimeBodyPart inLinetBodyPart = new MimeBodyPart();
			inLinetBodyPart.setContent("<H1>Hello</H1><img src=\"cid:image\">", "text/html");
			mimeMultipart.addBodyPart(inLinetBodyPart);

			MimeBodyPart fileBodyPart = new MimeBodyPart();
			fileBodyPart.setDataHandler(new DataHandler(new FileDataSource(filePath)));
			fileBodyPart.setHeader("Content-ID", "<image>");
			mimeMultipart.addBodyPart(fileBodyPart);

			this.mailmessage.setContent(mimeMultipart);

			// step 3: send message using Transport class
			Transport.send(mailmessage);

			System.out.println("mail send sucessfully");
			isMailSent = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isMailSent;
	}

}
