package com.demo.stock.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.demo.stock.entity.UserEntity;

public interface UserService {

	/**
	 * Get all users with pagination
	 * 
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Page<UserEntity> getUsers(int page, int pageSize);
	
	/**
	 * Get all users
	 * 
	 * @return
	 */
	public List<UserEntity> getUsers();
	
	/**
	 * Get user by user ID
	 * 
	 * @param 
	 * @return
	 */
	public UserEntity getUserById(Integer id);
	
	/**
	 * Register new user
	 * 
	 * @param user
	 * @return
	 */
	public UserEntity register(UserEntity user);
	
	/**
	 * Update user details
	 * 
	 * @param user
	 * @return
	 */
	public UserEntity update(UserEntity user);
	
	/**
	 * Activated user
	 * 
	 * @param id
	 * @return
	 */
	public UserEntity activate(Integer id);
	
	/**
	 * Delete user
	 * 
	 * @param id
	 */
	public void delete(Integer id);
}
