package com.graduation.education.frame.exception;

import org.springframework.http.HttpStatus;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/3/31 17:41
 * 禁止访问
 */
public class ForbiddenException extends GraduationException {

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.FORBIDDEN;
    }
}
