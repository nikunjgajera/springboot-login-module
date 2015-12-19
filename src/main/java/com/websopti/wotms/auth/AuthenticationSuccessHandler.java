package com.websopti.wotms.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.websopti.wotms.entity.User;
import com.websopti.wotms.service.UserService;

@Service
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Autowired
	private UserService userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		
		HttpSession session = request.getSession(false);
		

		/*Enumeration<String> ite = request.getHeaderNames();
		
		while (ite.hasMoreElements()) {
			String string = (String) ite.nextElement();
			System.out.println(string);
		}*/
		
		System.out.println(request.getParameter("spring-security-redirect"));
		
		if (authentication != null) {
        	
			String userName = ((User)authentication.getPrincipal()).getUsername(); 
        	
        	if (userName.equals("admin")) {
        		
        		session.setAttribute("admin","admin");
        		
        		response.sendRedirect(request.getContextPath() + "/admin/adminDashboard");
       	   
        	} else {
        		
        		/*we have used authentication by user email so here username is email*/
        		User user = userService.findByEmail(userName);
        		String url = null;
        		
        		if (session != null) {
        	        
        			url = (String) request.getSession().getAttribute("redirectUrl");
        			
        	    }
        		
        		if (url != null) {

        	        response.sendRedirect(url);

        	    }else{
        	    	
        	    	response.sendRedirect(request.getContextPath() + "/user/UserDashboard");
        	    	
        	    }
        		
        		/*if(user.getRole().equals("DEVELOPER")){
        			response.sendRedirect(request.getContextPath() + roleUrlMap.get("DEVELOPER"));
        		}else if(user.getRole().equals("DESIGNER")){
        			response.sendRedirect(request.getContextPath() + roleUrlMap.get("DESIGNER"));
        		}else if(user.getRole().equals("TESTER")){
        			response.sendRedirect(request.getContextPath() + roleUrlMap.get("TESTER"));
        		}else if(user.getRole().equals("TEAM_LEAD")){
        			response.sendRedirect(request.getContextPath() + roleUrlMap.get("TEAM_LEAD"));
        		}else if(user.getRole().equals("MANAGER")){
        			response.sendRedirect(request.getContextPath() + roleUrlMap.get("MANAGER"));
        		}else if(user.getRole().equals("PROJECT_LEAD")){
        			response.sendRedirect(request.getContextPath() + roleUrlMap.get("PROJECT_LEAD"));
        		}*/
        		
       	    }
        }	
	}
	
}
