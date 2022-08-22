package com.example.springsecurity2.controller;


import com.example.springsecurity2.entity.SysUser;
import com.example.springsecurity2.result.Result;
import com.example.springsecurity2.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author author
 * @since 2022-08-20
 */
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public Result<Map<String, String>> login(@RequestBody SysUser user) {
        return loginService.login(user);
    }

    @PostMapping("/logout")
    public Result logout() {
        return loginService.logout();
    }

    @GetMapping
    public String sa(){
        return "qwer";
    }
}
