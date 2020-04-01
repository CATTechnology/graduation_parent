package com.graduation.education.frame.exception;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/3/31 17:46
 */
public class UnsupportedMediaTypeException extends BadRequestException {
    public UnsupportedMediaTypeException(String message) {
        super(message);
    }

    public UnsupportedMediaTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
