package com.example.springsecurity2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springsecurity2.entity.LoginUser;
import com.example.springsecurity2.entity.SysUser;
import com.example.springsecurity2.mapper.UserMapper;
import com.example.springsecurity2.result.Result;
import com.example.springsecurity2.service.LoginService;
import com.example.springsecurity2.utils.JwtUtil;
import com.example.springsecurity2.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.example.springsecurity2.result.Result.OKResult;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author uuz
 * @since 2022-08-20
 */

@Service
public class LoginServiceImpl extends ServiceImpl<UserMapper, SysUser> implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;
//    private RedisTemplate redisTemplate;

    @Override
    public Result<Map<String, String>> login(SysUser user) {
        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        //在这一步最终会进入 UserDetailsServiceImpl 封装并返回
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //如果认证没通过，给出对应的提示
        if (authenticate == null) {
            throw new RuntimeException("登录失败");
        }
        //如果认证通过了，使用userid生成一个jwt jwt存入ResponseResult返回 Principal:主要的
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        Long id = loginUser.getUser().getId();
        String jwt = JwtUtil.createJWT(String.valueOf(id));
        //把完整的用户信息存入redis  userid作为key
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);

        redisCache.setCacheObject("login:" + id, loginUser);
//        redisTemplate.opsForValue().set("login:" + id, loginUser);
        return OKResult("登录成功", map);


    }

    @Override
    public Result logout() {
        //获取 SecurityContextHolder 中的用户信息
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long id = loginUser.getUser().getId();
        //输出redis中的key
        redisCache.deleteObject("login:" + id);
//        redisTemplate.opsForValue().set("login:" + id, loginUser);
        return OKResult("注销成功");
    }
}
