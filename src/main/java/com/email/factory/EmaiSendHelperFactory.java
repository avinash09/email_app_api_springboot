package com.email.factory;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;

import com.email.helper.EmailSendHelper;
import com.email.helper.MailSendHelper;
import com.email.helper.SimpletEmailSendHelper;
import com.email.module.MailServerInfo;

public class EmaiSendHelperFactory {

	public static EmailSendHelper getEmailSendHelper() {

		MailServerInfo mailInfo = getMailInfo();
		Session session = getMailSession(mailInfo);

		return new MailSendHelper(new MimeMessage(session));
	}
	
	public static EmailSendHelper getEmailSendHelper(JavaMailSender mailSender) {

		return new SimpletEmailSendHelper(mailSender);
	}

	public static Address getToAddress(String to) throws AddressException {
		return new InternetAddress(to);
	}

	private static MailServerInfo getMailInfo() {
		return new MailServerInfo("smtp.gmail.com", "465", true, true, true, "learncodewithavi@gmail.com",
				"learncodewithavi@gmail.com", "hiw07@Gm");
	}

	private static Session getMailSession(MailServerInfo mailInfo) {

		Session session = Session.getInstance(mailInfo.getMailProperties(), new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailInfo.getUsername(), mailInfo.getPassword());
			}
		});

		session.setDebug(mailInfo.isEnableDebug());

		return session;
	}

}
