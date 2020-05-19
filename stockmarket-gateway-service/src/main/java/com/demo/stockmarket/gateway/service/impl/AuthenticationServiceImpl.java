package com.demo.stockmarket.gateway.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.stockmarket.entity.User;
import com.demo.stockmarket.gateway.repository.UserRepository;
import com.demo.stockmarket.gateway.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired
    private UserRepository userRepository;

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
