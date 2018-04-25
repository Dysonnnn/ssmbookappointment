package com.ifox.zc.exception;

/**
 * @Author:zhongchao
 * @Organization: ifox
 * @Description:
 * @Date:Created in17:12 2018/4/24
 * @Modified By:    预约义务异常
 */
public class AppointException extends RuntimeException {
    public AppointException(String message) {
        super(message);
    }

    public AppointException(String message, Throwable cause) {
        super(message, cause);
    }
}
