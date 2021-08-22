package com.yx.valid.exception;

/**
 * 业务异常
 * @author ludangxin
 * @date 2021/8/5
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    protected final String message;

    public BusinessException(String message) {
        this.message = message;
    }

    public BusinessException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
