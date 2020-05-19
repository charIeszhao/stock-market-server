package com.demo.stockmarket.gateway.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponse {

    private String token;
    
    private Integer userId;
    
	private String role;
    
    private String email;
    
}

