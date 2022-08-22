package com.example.springsecurity2.Exception;

import com.example.springsecurity2.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Classname GlobalExceptionHandeler
 * @Description TODO
 * @Date 2022/8/20 21:05
 * @Created by uuz
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotLoginException.class)
    @ResponseBody
    public Result NotLoginException(NotLoginException e) {
        return Result.error(e.getCode(),e.getMsg());
    }
}
