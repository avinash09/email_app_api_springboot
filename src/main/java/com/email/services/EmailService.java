package com.email.services;

import java.net.URL;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.email.builder.MailSenderBuilder;
import com.email.factory.EmaiSendHelperFactory;
import com.email.helper.EmailSendHelper;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;

	public boolean sendEmail(String to, String subject, String message) {

		//send mail with factory pattern
		/*EmailSendHelper emailSendHelper = EmaiSendHelperFactory.getEmailSendHelper(javaMailSender);
		return emailSendHelper.sendEmail(to, subject, message);*/	
		boolean isMailSent = false;
		try {
			isMailSent = new MailSenderBuilder(to, subject, message, javaMailSender, false).composeMessage().build()
					.sendEmail();

			//send mail with attachment and inline
			/*URL url = this.getClass().getResource("/static/footer-img.png");
			new MailSenderBuilder(to, subject, "<html><body><img src='cid:identifier1234'></body></html>", javaMailSender, true).composeMessage()
					.addAttachment(url.getPath()).addInline(url.getPath(),"identifier1234").build().sendEmail();*/
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return isMailSent;
	}

	
}
