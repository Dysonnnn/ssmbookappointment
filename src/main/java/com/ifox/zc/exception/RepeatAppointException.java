package com.ifox.zc.exception;

/**
 * @Author:zhongchao
 * @Organization: ifox
 * @Description:
 * @Date:Created in17:13 2018/4/24
 * @Modified By:    重复预约异常
 */
public class RepeatAppointException extends RuntimeException{
    public RepeatAppointException(String message) {
        super(message);
    }

    public RepeatAppointException(String message, Throwable cause) {
        super(message, cause);
    }
}
