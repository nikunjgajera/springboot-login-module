package com.websopti.wotms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.websopti.wotms.auth.AuthenticationService;
import com.websopti.wotms.auth.AuthenticationFailureHandler;
import com.websopti.wotms.auth.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	

	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired
	private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		authenticationSuccessHandler.setAlwaysUseDefaultTargetUrl(false);
		authenticationSuccessHandler.setTargetUrlParameter("spring-security-redirect");
		//authenticationSuccessHandler.setUseReferer(true);
		
		http.authorizeRequests()
			.antMatchers("/","/index","/static/**",
					"/img/**","/css/**","/js/**",
					"/login","/register").permitAll()
			.anyRequest().authenticated()
			
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
			
			.and().exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint)
			
			.and().formLogin().loginPage("/login").loginProcessingUrl("/login").usernameParameter("email").passwordParameter("password")
			.successHandler(authenticationSuccessHandler)
			.failureHandler(authenticationFailureHandler)
			
			.and().logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll()
			
			.and().csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)throws Exception {
		auth.userDetailsService(authenticationService).passwordEncoder(
				passwordEncoder());
		
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
