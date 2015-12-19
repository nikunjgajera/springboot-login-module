package com.websopti.wotms.enums;

public enum Repositories {

	SVN, GIT, BITBUCKET, CSV;
	
	public static String titleCase(String string) {
	    
		string = string.toLowerCase();
		
		return Character.toUpperCase(string.charAt(0)) + string.substring(1);
	  
	}
}
