package com.demo.stockmarket.gateway.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.stockmarket.entity.RestResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseBody
    public RestResponse handleAuthenticationException(AuthenticationException e, HttpServletRequest req) {
        return RestResponse.error(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RestResponse handleRuntimeException(Exception e, HttpServletRequest req) {
        log.error("[Exception]:{} ", e.getMessage());
        return RestResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
