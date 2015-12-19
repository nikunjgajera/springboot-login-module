package com.websopti.wotms.service;

import java.io.IOException;

import com.websopti.wotms.entity.User;

public interface UserEmailService {
	
	public void sendCreateUserEmail(User user) throws IOException;
	
}
