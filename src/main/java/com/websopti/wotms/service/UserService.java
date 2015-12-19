package com.websopti.wotms.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.websopti.wotms.entity.User;
import com.websopti.wotms.enums.Role;
import com.websopti.wotms.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	/**
	 * Find user by email
	 * 
	 * @param email
	 * @return
	 */
	public User findByEmail(String email) {
		return userRepository.findOneByEmail(email);
	}

	/**
	 * register user
	 * 
	 * @param user
	 */
	public void register(User user) {
		user.setIsActive(true);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(Role.DEVELOPER);
		userRepository.save(user);
	}
	
	/**
	 * Update user
	 * 
	 * @param user
	 */
	public void update(User user) {
		userRepository.save(user);
	}

	/**
	 * Get all users
	 * 
	 * @return
	 */
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	/**
	 * Find user by id
	 * 
	 * @param id
	 * @return
	 */
	public User findById(Long id) {
		return userRepository.findOne(id);
	}
	
	/**
	 * Update name for given user
	 * 
	 * @param user
	 */
	@Transactional
	public void updateName(User user) {
		userRepository.updateName(user.getId(), user.getName());
	}
	
	/**
	 * Encode password and update into database for given user
	 * 
	 * @param user
	 */
	@Transactional
	public void updatePassword(Long userId, String newPassword) {
		userRepository.updatePassword(userId, passwordEncoder.encode(newPassword));
	}
	
	/**
	 * Change password to new password if old password matches 
	 * else throw IncorrectOldPassword exception 
	 * 
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 * @throws IncorrectOldPassword
	 */
	@Transactional
	public boolean changePassword(Long userId, String oldPassword, String newPassword){
		
		if(this.checkPassword(userId, oldPassword)){
			this.updatePassword(userId, newPassword);
			return true;
		}
		return false;	
	}
	
	/**
	 * Check if password matches with database for given user
	 * 
	 * @param userId
	 * @param oldPassword
	 * @return
	 */
	public boolean checkPassword(Long userId, String password) {
		
		User user = userRepository.findOne(userId);
		
		return passwordEncoder.matches(password, user.getPassword());
	}
	
	/**
	 * check user exist or not by email
	 * 
	 * @return
	 */
	public boolean isExistUser(String email) {
		
		return this.findByEmail(email) != null;
	}
	
	/**
	 * check user exist or not by id
	 * 
	 * @return
	 */
	public boolean isExistUser(Long id) {
		
		return this.findById(id) != null;
	}
}
