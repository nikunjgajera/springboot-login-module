package com.websopti.wotms.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@PropertySource("classpath:mail.properties")
public class ThymeleafMailConfig {
	
	@Value("${mail.protocol}")
    private String protocol;
	
    @Value("${mail.host}")
    private String host;
    
    @Value("${mail.port}")
    private int port;
    
    @Value("${mail.smtp.auth}")
    private boolean auth;
    
    @Value("${mail.smtp.starttls.enable}")
    private boolean starttls;
    
    @Value("${mail.username}")
    private String username;
    
    @Value("${mail.password}")
    private String password;
    
  
	@Bean 
	public JavaMailSender getJavaMailSenderImpl(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        
        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", auth);
        mailProperties.put("mail.smtp.starttls.enable", starttls);
        
        javaMailSender.setJavaMailProperties(mailProperties);
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setProtocol(protocol);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        
        return javaMailSender;
    }
    
}
