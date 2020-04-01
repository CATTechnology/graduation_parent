package com.graduation.education.frame.exception;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/3/31 17:41
 * Email Exception
 */
public class EmailException extends ServiceException {


    public EmailException(String message) {
        super(message);
    }

    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
