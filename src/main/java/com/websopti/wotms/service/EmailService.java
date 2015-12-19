package com.websopti.wotms.service;

/**
 * Email service
 * @author nikunjg
 *
 */
public interface EmailService {

	public void sendTextMail(String from, String to, String subject, String msg) ;

	public void sendHTMLMail(String from, String to, String subject, String messageText);
	
	public void sendTextMail(String from, String to, String cc, String bcc, String subject, String msg);

	public void sendTextMail(String from, String to, String cc, String subject,String msg);
}