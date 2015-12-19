package com.websopti.wotms.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.websopti.wotms.entity.User;
import com.websopti.wotms.enums.ErrorKey;
import com.websopti.wotms.service.UserService;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	public UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userService.findByEmail(email);
		
		if(user != null)
			return user;
		else
			throw new UsernameNotFoundException(ErrorKey.USER_NOT_FOUND.name());
	}

}
