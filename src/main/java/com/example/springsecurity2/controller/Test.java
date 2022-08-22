package com.example.springsecurity2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname Test
 * @Description TODO
 * @Date 2022/8/21 14:06
 * @Created by uuz
 */

@RestController
public class Test {

    @PostMapping("/1")
    @PreAuthorize("hasAuthority('system:dept:list')")
    public String s() {
        return  "1";
    }

    @GetMapping("/1")
    public String s1() {
        return  "1";
    }

    @GetMapping("/2")
    @PreAuthorize("@expressionRoot.hasAuthority('system:dept:list')")
    public String s2(){
        return "2";
    }
}