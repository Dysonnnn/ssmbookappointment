package com.ifox.zc.exception;

/**
 * @Author:zhongchao
 * @Organization: ifox
 * @Description:
 * @Date:Created in17:12 2018/4/24
 * @Modified By:    库存不足异常
 */
public class NoNumberException extends RuntimeException{
    public NoNumberException(String message) {
        super(message);
    }

    public NoNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
