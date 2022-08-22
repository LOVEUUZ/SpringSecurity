package com.example.springsecurity2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springsecurity2.Exception.ExceptionEnum;
import com.example.springsecurity2.Exception.NotLoginException;
import com.example.springsecurity2.entity.LoginUser;
import com.example.springsecurity2.entity.SysUser;
import com.example.springsecurity2.mapper.MenuMapper;
import com.example.springsecurity2.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @Classname UserDetailsServiceImpl
 * @Description TODO
 * @Date 2022/8/20 14:31
 * @Created by uuz
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUsername,username);
        SysUser user = userMapper.selectOne(queryWrapper);
        //如果没有查询到用户就抛出异常
        if(Objects.isNull(user)){
            throw new NotLoginException(ExceptionEnum.WRONGPASSWORD);
        }

        //查询对应的权限信息
//        List<String> list = new ArrayList<>(Arrays.asList("test","admin","1"));
        List<String> permissions = menuMapper.selectPermsByUserId(user.getId());

        //把数据封装成UserDetails返回
        return new LoginUser(user,permissions);
    }
}
