package com.demo.stockmarket.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RestResponse<T> {

    private static final int OK_CODE = 200;

    private T data;
    private String message;
    private int code;


    public RestResponse(int code, String message, T data) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public static <T> RestResponse<T> ok() {
        return new RestResponse<T>(OK_CODE, null, null);
    }

    public static <T> RestResponse<T> ok(T data) {
        return new RestResponse<T>(OK_CODE, null, data);
    }

    public static <T> RestResponse<T> ok(T data, String message) {
        return new RestResponse<T>(OK_CODE, message, data);
    }

    public static <T> RestResponse<T> error(int code, String message) {
        return new RestResponse<T>(code, message, null);
    }

    public static <T> RestResponse<T> error(int code, String message, T data) {
        return new RestResponse<T>(code, message, data);
    }

}
