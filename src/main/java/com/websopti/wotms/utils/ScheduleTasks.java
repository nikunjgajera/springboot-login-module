package com.websopti.wotms.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.websopti.wotms.service.UserService;

@Component
public class ScheduleTasks {

	@Autowired
	private UserService userService;
	
	//@Scheduled(cron="* */5 12 * * *")
    //@Scheduled(cron="*/10 * * * * *")
    public void reportCurrentTime() {
        
    	//for test
    	
    }
    
    //@Scheduled(cron="* */5 12 * * *")
    public void sendMailForDueTask() {
        
	}
}