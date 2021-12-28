package com.tansci.common.exception;

import lombok.Getter;

/**
 * @path：com.tansci.common.exception.BusinessException.java
 * @className：BusinessException.java
 * @description：业务异常处理
 * @author：tanyp
 * @dateTime：2021/10/22 17:27
 * @editNote：
 */
@Getter
public class BusinessException extends RuntimeException {

    /**
     * 异常码
     */
    private int code = 500;

    /**
     * 异常描述
     */
    private String message;

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BusinessException(int code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
        this.message = message;
    }

}
