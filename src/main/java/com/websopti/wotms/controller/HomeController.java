package com.websopti.wotms.controller;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.websopti.wotms.entity.User;
import com.websopti.wotms.service.UserEmailService;
import com.websopti.wotms.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserEmailService userEmailService;
	
	/**
	 * Index page
	 * @return
	 */
	@RequestMapping(value={"", "/", "index"})
	public String redirectToHome() {
		return "index";
	}
	
	/**
	 * get Authentication form
	 * @param request
	 * @param model
	 * @return
	 * @author nikunjg
	 */
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String authenticateUser(HttpServletRequest request, ModelMap model, Locale local) {
		
		Enumeration<String> ite = request.getHeaderNames();
		
		while (ite.hasMoreElements()) {
			String string = (String) ite.nextElement();
			System.out.println(string);
		}
		
		
		String referrer = request.getHeader("referer");
		System.out.println(referrer);
	    
		if(referrer!=null){
			
	        request.getSession().setAttribute("refererUrl", referrer);
	        
	    }
		
		return "Login";
	}
	
	/**
	 * Get Registration form
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String registerUser(HttpServletRequest request, ModelMap model, Locale locale) {
		
		model.addAttribute("user",new User());
		
		return "Register";
	}
	
	/**
	 * Post Registration Form
	 * @param user
	 * @param model
	 * @return
	 * @author Ni(|<
	 */
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") User user, ModelMap model,RedirectAttributes redirectAttributes) {
		
		try{
			
			if (!userService.isExistUser(user.getEmail())) {
				
				userService.register(user);
				
				userEmailService.sendCreateUserEmail(user);
				
			} else {
				
				model.addAttribute("error", "registration.userexist.error");
				
				return "Register";
			}
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
			model.addAttribute("error", "registration.general.error");
			
			return "Register";
		}
		
		redirectAttributes.addFlashAttribute("succcess", "registration.general.success");
		
		return "redirect:/register";
	}
}
