package com.demo.stockmarket.exception;

import java.text.MessageFormat;

public class BaseRuntimeException extends RuntimeException {
	
	protected int errorCode;

    protected Object[] params;

    public BaseRuntimeException(int errorCode, String message) {
        super(getMessageFormat(message));

        this.errorCode = errorCode;
    }
    
    public BaseRuntimeException(int errorCode, String message, Object... params) {
        super(getMessageFormat(message));

        this.errorCode = errorCode;
        this.params = params;
    }

    public BaseRuntimeException(int errorCode, String message, Throwable cause, Object... params) {
        super(getMessageFormat(message, params), cause);
        this.errorCode = errorCode;
        this.params = params;
    }

    public BaseRuntimeException(int errorCode, Throwable cause, Object... params) {
        super(cause);
        this.errorCode = errorCode;
        this.params = params;
    }

    public int getErrorCode() {
        return errorCode;
    }
    public Object[] getParams() {
        return params;
    }

    public static String getMessageFormat(String message, Object... params) {
        if (params == null || params.length == 0) {
            return message;
        }
        if (message == null) {
            return "";
        }
        return MessageFormat.format(message, params);
    }
}
