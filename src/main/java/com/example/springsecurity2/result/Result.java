package com.example.springsecurity2.result;

import com.example.springsecurity2.Exception.NotLoginException;
import lombok.Data;

/**
 * @Classname Result
 * @Description TODO
 * @Date 2022/8/20 15:18
 * @Created by uuz
 */

@Data
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public Result() {
    }

    public static <T> Result<T> OKResult(String msg) {
        return new Result<>(200, msg);
    }

    public static <T> Result<T> OKResult() {
        return new Result<>(200, null);
    }

    public static <T> Result<T> OKResult(String msg, T data) {
        return new Result<>(200, msg, data);
    }

    public static Result<Object> error(int code, String message) {
        return new Result<>(code, message);
    }

}
