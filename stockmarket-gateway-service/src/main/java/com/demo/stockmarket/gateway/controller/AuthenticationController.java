package com.demo.stockmarket.gateway.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.stockmarket.entity.RestResponse;
import com.demo.stockmarket.entity.User;
import com.demo.stockmarket.gateway.entity.LoginResponse;
import com.demo.stockmarket.gateway.service.AuthenticationService;
import com.demo.stockmarket.gateway.service.TokenService;

@RestController
@RequestMapping
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authService;
	
	@Autowired
	private TokenService tokenService;
	
	@RequestMapping(value = "/api/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<?> login(@Valid @RequestBody User loginUser) {
        User user = authService.getUserByEmail(loginUser.getEmail());
        String token = null;
        
        if (user == null) {
        	return RestResponse.error(HttpStatus.UNAUTHORIZED.value(), "Invalid username or password.");
        }
        
        if (user.getPassword().equals(loginUser.getPassword())) {
        	token = tokenService.createToken(user.getEmail(), user.getRole());
        	
        } else {
        	return RestResponse.error(HttpStatus.UNAUTHORIZED.value(), "Invalid username or password.");
        }
        
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setUserId(user.getId());
        loginResponse.setEmail(user.getEmail());
        loginResponse.setRole(user.getRole());
        
        return RestResponse.ok(loginResponse, "Login successfully.");
    }
}
