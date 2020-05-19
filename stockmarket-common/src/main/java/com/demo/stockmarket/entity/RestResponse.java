package com.demo.stockmarket.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RestResponse<T> {

    private static final int OK_CODE = 200;
    private static final int CREATED_CODE = 201;
    private static final int NO_CONTENT_CODE = 204;

    private T data;
    private String message;
    private int code;
    private Long count;
    private boolean success;

    public RestResponse(int code, String message, T data, boolean success) {
        this.data = data;
        this.message = message;
        this.code = code;
        this.success = success;
    }

    public RestResponse(int code, String message, T data, Long count, boolean success) {
        this.data = data;
        this.message = message;
        this.code = code;
        this.count = count;
        this.success = success;
    }

    public static <T> RestResponse<T> ok() {
        return new RestResponse<T>(OK_CODE, "", null, true);
    }

    public static <T> RestResponse<T> ok(T data) {
        return new RestResponse<T>(OK_CODE, "", data, true);
    }

    public static <T> RestResponse<T> ok(T data, String message) {
        return new RestResponse<T>(OK_CODE, message, data, true);
    }
    
    public static <T> RestResponse<T> ok(T data, long total, String message) {
        return new RestResponse<T>(OK_CODE, message, data, total, true);
    }
    
    public static <T> RestResponse<T> created(T data) {
        return new RestResponse<T>(CREATED_CODE, "", data, true);
    }

    public static <T> RestResponse<T> created(T data, String message) {
        return new RestResponse<T>(CREATED_CODE, message, data, true);
    }
    
    public static <T> RestResponse<T> updated(T data) {
        return new RestResponse<T>(OK_CODE, "", data, true);
    }

    public static <T> RestResponse<T> updated(T data, String message) {
        return new RestResponse<T>(OK_CODE, message, data, true);
    }
    
    public static <T> RestResponse<T> deleted(T data) {
        return new RestResponse<T>(NO_CONTENT_CODE, "", data, true);
    }

    public static <T> RestResponse<T> deleted(T data, String message) {
        return new RestResponse<T>(NO_CONTENT_CODE, message, data, true);
    }

    public static <T> RestResponse<T> error(int code, String message) {
        return new RestResponse<T>(code, message, null, false);
    }

    public static <T> RestResponse<T> error(int code, String message, T data) {
        return new RestResponse<T>(code, message, data, false);
    }

}
