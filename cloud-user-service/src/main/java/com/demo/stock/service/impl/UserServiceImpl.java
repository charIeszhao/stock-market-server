package com.demo.stock.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.demo.stock.entity.UserEntity;
import com.demo.stock.repository.UserRepository;
import com.demo.stock.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public Page<UserEntity> getUsers(int page, int pageSize) {
		Pageable pageable = new PageRequest(page, pageSize);
		return userRepository.findAll(pageable);
	}

	@Override
	public UserEntity getUserById(Integer id) {
		return userRepository.findById(id);
	}
	
	@Override
	public List<UserEntity> getUsers() {
		return userRepository.findAll();
	}
	
	@Override
	public UserEntity register(UserEntity user) {
		return userRepository.saveAndFlush(user);
	}

	@Override
	public UserEntity update(UserEntity user) {
		return userRepository.save(user);
	}

	@Override
	public UserEntity activate(Integer id) {
		userRepository.activate(id);
		return userRepository.findById(id);
	}

	@Override
	public void delete(Integer id) {
		userRepository.delete(id);
	}
}
