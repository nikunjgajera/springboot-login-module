package com.websopti.wotms.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.websopti.wotms.entity.User;

@Service
@PropertySource("classpath:mail.properties")
public class UserEmailServiceImpl implements UserEmailService {
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private SpringTemplateEngine templateEngine;

	@Value("${mail.from}")
    private String from;
	
	@Value("${mail.regards}")
    private String regards;
	
	@Autowired
	private Environment env;
	
	@Value("${application.rooturl}")
	private String rootUrl;
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	@Async
	public void sendCreateUserEmail(User user) {
		
		final Context ctx = this.getDefaultContextForTask(user);
		
		final String htmlContent = this.templateEngine.process("mail/sendUserCreateEmail", ctx);
		
		emailService.sendHTMLMail(from, user.getEmail(), messageSource.getMessage("send.user.create.mail", new Object[]{user.getName()}, Locale.US), htmlContent);
		
	}
	
	private Context getDefaultContextForTask(User user){
		
		final Context ctx = new Context();
		
		ctx.setVariable("name", user.getName());
		
		ctx.setVariable("regards", regards);
		
		ctx.setVariable("rootUrl", rootUrl);
		
		return ctx;
		
	} 
	
}
