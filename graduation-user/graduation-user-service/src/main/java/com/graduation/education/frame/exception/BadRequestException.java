package com.graduation.education.frame.exception;

import org.springframework.http.HttpStatus;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/3/31 17:22
 */
public class BadRequestException extends GraduationException {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
