package com.websopti.wotms.enums;

public enum ProjectStatus {

	DEVELOPMENT, MAINTAINCE, CLOSED;
	
	public static String titleCase(String string) {
	    
		string = string.toLowerCase();
		
		return Character.toUpperCase(string.charAt(0)) + string.substring(1);
	  
	}
}
