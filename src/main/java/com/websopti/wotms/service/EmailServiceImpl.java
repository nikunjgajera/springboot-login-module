package com.websopti.wotms.service;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender mailSender;
	
	static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
	
	@Override
	public void sendTextMail(String from, String to, String subject, String msg) {
		this.sendTextMail(from, to, null, null, subject, msg);
	}

	@Override
	public void sendTextMail(String from, String to, String cc, String subject, String msg) {
		this.sendTextMail(from, to, cc, null, subject, msg);
	}
	
	@Override
	public void sendTextMail(String from, String to, String cc, String bcc, String subject, String msg) {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		if(StringUtils.defaultString(cc).length() > 0)
			message.setCc(cc);
		if(StringUtils.defaultString(bcc).length() > 0)
			message.setBcc(bcc);
		
		mailSender.send(message);
	}
	
	@Override
	public void sendHTMLMail(String from, String to, String subject, String messageText) {
		//this.sendHTMLMail(from, to, subject, messageText, null);
		try {		
		MimeMessage mimemessage = mailSender.createMimeMessage();// MimeMessage(session);
		MimeMessageHelper message = new MimeMessageHelper(mimemessage, true,"UTF-8");
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(messageText, true);
		
		//logger.debug("Sending email to [ "+to+" ]");
		mailSender.send(mimemessage);
		//logger.debug("Sent email successfully to [ "+to+" ]");
		
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}		
	}
}
