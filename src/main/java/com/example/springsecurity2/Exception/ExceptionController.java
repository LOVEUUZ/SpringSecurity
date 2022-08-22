package com.example.springsecurity2.Exception;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname ExceptionController
 * @Description TODO
 * @Date 2022/8/20 21:58
 * @Created by uuz
 */

@RestController
public class ExceptionController {

    @RequestMapping("/NotLoginException")
    public void NotLoginException(HttpServletRequest request) {
//        boolean notLoginException = request.getAttribute("UNAUTHORIZED") instanceof UNAUTHORIZED;
//        System.out.println(">>>>>>>>> >" + request.getAttribute("NotLoginException"));
//        if (request.getAttribute("UNAUTHORIZED") instanceof RuntimeException) {
            throw new NotLoginException(ExceptionEnum.UNAUTHORIZED);
//        }
    }
}
