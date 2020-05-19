package com.demo.stockmarket.user.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.demo.stockmarket.entity.User;

public interface UserService {
	
	/**
	 * Get all users with pagination
	 * 
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Page<User> getUsers(int page, int pageSize);
	
	/**
	 * Get all users
	 * 
	 * @return
	 */
	public List<User> getUsers();
	
	/**
	 * Get user by user email address
	 * 
	 * @param email
	 * @return
	 */
	public User getUserByEmail(String email);
	
	/**
	 * Register new user
	 * 
	 * @param user
	 * @return
	 */
	public User register(User user);
	
	/**
	 * Update user details
	 * 
	 * @param user
	 * @return
	 */
	public User update(User user, int id);
	
	/**
	 * Activated user
	 * 
	 * @param id
	 * @return
	 */
	public void activate(Integer id);
	
	/**
	 * Delete user
	 * 
	 * @param id
	 */
	public void delete(Integer id);
}
