package com.demo.stockmarket.gateway.service;

public interface TokenService {
	
	public String createToken(String email, String role);
	
	public boolean verifyToken(String token);
	
	public String getUserEmail(String token);
	
	public String getUserRole(String token);
	
	public boolean isExpired(String token);

}
