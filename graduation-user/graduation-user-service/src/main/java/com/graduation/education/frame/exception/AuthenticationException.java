package com.graduation.education.frame.exception;

import org.springframework.http.HttpStatus;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/3/31 16:59
 */
public class AuthenticationException extends GraduationException {

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
