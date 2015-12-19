package com.websopti.wotms.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(Exception.class)
	public String catchExpcetion(Exception exception) {
		exception.printStackTrace();
		return "view";
	}
}
