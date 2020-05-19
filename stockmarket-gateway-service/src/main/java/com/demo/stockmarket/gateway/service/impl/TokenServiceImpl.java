package com.demo.stockmarket.gateway.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.demo.stockmarket.gateway.service.TokenService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenServiceImpl implements TokenService {

	private String SECRET = "mysecretkey";
	private long EXPIRATION_TIME_MILLIS = 3600000;

	public String createToken(String email, String role) {

		Claims claims = Jwts.claims().setSubject(email);
		claims.put("role", role);

		String token = Jwts.builder().setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MILLIS)).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();

		return token;
	}

	public boolean verifyToken(String token) {
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public String getUserEmail(String token) {
		try {
			return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
		} catch (Exception e) {
			return null;
		}
	}

	public String getUserRole(String token) {
		try {
			Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
			return claims.get("role").toString();
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean isExpired(String token) {
		try {
			Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
	    	return claims.getExpiration().before(new Date());
		} catch (Exception e) {
			return true;
		}
	}

}
