package com.websopti.wotms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.websopti.wotms.entity.User;
import com.websopti.wotms.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EntityManager entityManager;
	
	@ModelAttribute("allUser")
    public List<User> populateUsers() {
		
		Iterable<User> users = userService.findAll();
		List<User> userList = new ArrayList<User>();
		
		for (User user : users) {
			userList.add(user);
		}
		return userList;
    }
	
	/**
	 * get Userdashboard
	 * @param model
	 * @param request
	 * @return
	 * @author nikunjg
	 */
	@RequestMapping(value="/UserDashboard", method=RequestMethod.GET)
	public String userDashboard(ModelMap model, HttpServletRequest request) {
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		user = entityManager.merge(user);
		
		model.addAttribute("username", user.getName());
		
		return "user/UserDashboard";
	}
	
	/**
	 *Get profile 
	 * @param id
	 * @return
	 * @author nikunjg
	 */
	@RequestMapping(value="/profile", method=RequestMethod.GET)
	public String getUserProfile(@RequestParam("id")Long id) {
		
		return "UserProfile";
	}
	
	/**
	 * Edit profile
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/editProfile", method=RequestMethod.POST)
	public User editUserProfile(@RequestBody User user) {
		
		User profile = userService.findById(user.getId());
		
		profile.setName(user.getName());
		
		profile.setEmail(user.getEmail());
		
		userService.update(profile);
		
		return profile;
	}
	
	/**
	 * Get change password form
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/changePassword", method=RequestMethod.GET)
	public String getChnagePassword(HttpServletRequest request, ModelMap model) {
		return "user/ChangePassword";
	}
	
	/**
	 * Post change password form
	 * @param id
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/changePassword", method=RequestMethod.POST)
	public String postChangePassword(@RequestParam("newPassword")String newPassword,@RequestParam("oldPassword")String oldPassword,HttpServletRequest request, ModelMap model, RedirectAttributes redirectAttribute) {
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(!userService.changePassword(user.getId(), oldPassword, newPassword)){
			
			model.addAttribute("error","incorrect.old.password");
			
			return "user/ChangePassword";
		}
					
		redirectAttribute.addFlashAttribute("success", "chnage.password.success");
		
		return "redirect:/user/changePassword";
	}
}
