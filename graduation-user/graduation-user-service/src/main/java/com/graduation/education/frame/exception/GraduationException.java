package com.graduation.education.frame.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/3/31 17:00
 * 异常基类
 */
public abstract class GraduationException extends RuntimeException {

    private Object errorData;

    public GraduationException(String message){
        super(message);
    }

    public GraduationException(String message , Throwable cause){
        super(message , cause);
    }

    @NonNull
    public abstract HttpStatus getStatus();

    @Nullable
    public Object getErrorData() {
        return errorData;
    }

    @NonNull
    public GraduationException setErrorData(Object errorData) {
        this.errorData = errorData;
        return this;
    }
}
