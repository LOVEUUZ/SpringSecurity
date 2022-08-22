package com.example.springsecurity2.Exception;

import lombok.Data;

/**
 * @Classname NotLoginException
 * @Description TODO
 * @Date 2022/8/20 20:17
 * @Created by uuz
 */

@Data
public class NotLoginException extends RuntimeException {
    private int code;
    private String msg;

    public NotLoginException(ExceptionEnum e) {
        this.code = e.getCode();
        this.msg = e.getMsg();
    }


}
