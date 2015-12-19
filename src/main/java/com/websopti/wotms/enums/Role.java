package com.websopti.wotms.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

	ADMIN, DEVELOPER, DESIGNER, TESTER, TEAM_LEAD, MANAGER, PROJECT_LEAD;

	@Override
	public String getAuthority() {
		return this.name();
	}
	
	public static String titleCase(String string) {
	    
		string = string.toLowerCase();
		
		return Character.toUpperCase(string.charAt(0)) + string.substring(1);
	  
	}
}
