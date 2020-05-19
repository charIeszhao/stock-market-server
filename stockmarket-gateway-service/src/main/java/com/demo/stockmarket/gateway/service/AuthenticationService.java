package com.demo.stockmarket.gateway.service;

import com.demo.stockmarket.entity.User;

public interface AuthenticationService {

	User getUserByEmail(String email);

}
