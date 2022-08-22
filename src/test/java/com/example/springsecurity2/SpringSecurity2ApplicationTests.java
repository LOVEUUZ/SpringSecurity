package com.example.springsecurity2;

import com.example.springsecurity2.entity.SysUser;
import com.example.springsecurity2.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
class SpringSecurity2ApplicationTests {

    @Autowired
    private UserMapper userMapper;


    @Test
    void contextLoads() {
        List<SysUser> sysUsers = userMapper.selectList(null);
        System.out.println(sysUsers);
    }

    @Test
    public void TestBCryptPasswordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode1 = bCryptPasswordEncoder.encode("11");
        String encode2 = bCryptPasswordEncoder.encode("1234");
        System.out.println(encode1);
//        System.out.println(encode2);
//        boolean matches = bCryptPasswordEncoder.matches("1234", "$2a$10$FbaLdVL73lQSS/4vv7EdH.WLyQ5wrSnCXsrH8njWpJsqKXjvVRF7.");
//        boolean matches2 = bCryptPasswordEncoder.matches("1234", "$2a$10$YUjCo3amlLVvMC6WtITQcOL/Pv.5vRL8h1LJeSH/0Ev5.ZteMRSf.");
//        System.out.println(matches);
//        System.out.println(matches2);

    }

}
