package com.graduation.education.frame.exception;

import org.springframework.http.HttpStatus;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/3/31 17:42
 * 内部服务异常
 */
public class ServiceException extends GraduationException {


    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
