package com.demo.stockmarket.gateway.exception;

import com.demo.stockmarket.exception.BaseRuntimeException;

public class AuthenticationException extends BaseRuntimeException {
	public AuthenticationException(int errorCode, String message) {
			super(errorCode, message);
		}
}

