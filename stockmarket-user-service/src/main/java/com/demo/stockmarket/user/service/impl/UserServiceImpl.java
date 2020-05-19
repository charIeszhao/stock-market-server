package com.demo.stockmarket.user.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.stockmarket.entity.User;
import com.demo.stockmarket.user.repository.UserRepository;
import com.demo.stockmarket.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public Page<User> getUsers(int page, int pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
		return userRepository.findAll(pageable);
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	@Override
	public User register(User user) {
		user.setActive(0);
		user.setRole("USER");
		return userRepository.save(user);
	}

	@Override
	public User update(User user, int id) {
		User existingUser = this.getUserById(id).get();
		if (existingUser != null) {
			user.setId(id);
			user.setActive(existingUser.getActive());
			user.setRole("USER");
			return userRepository.save(user);
			
		} else {
			return null;
		}
		
		
	}

	@Override
	public void activate(Integer id) {
		userRepository.activate(id);
	}

	@Override
	public void delete(Integer id) {
		userRepository.deleteById(id);
	}
	
	public Optional<User> getUserById(int id) {
		return userRepository.findById(id);
	}
}
