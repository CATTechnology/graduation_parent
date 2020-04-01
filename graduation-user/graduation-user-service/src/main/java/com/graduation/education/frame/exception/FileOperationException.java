package com.graduation.education.frame.exception;


/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/3/31 17:44
 * 内部服务异常
 */
public class FileOperationException extends ServiceException {

    public FileOperationException(String message) {
        super(message);
    }

    public FileOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
