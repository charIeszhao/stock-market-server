package com.demo.stockmarket.gateway.exception;

import com.demo.stockmarket.exception.BaseRuntimeException;
import com.demo.stockmarket.exception.IErrorCode;

public class AuthenticationException extends BaseRuntimeException {
	public AuthenticationException(int errorCode, String message) {
			super(errorCode, message);
		}
}

