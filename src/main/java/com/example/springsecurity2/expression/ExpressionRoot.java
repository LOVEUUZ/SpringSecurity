package com.example.springsecurity2.expression;

import com.example.springsecurity2.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Classname ExpressionRoot
 * @Description TODO
 * @Date 2022/8/21 21:58
 * @Created by uuz
 */

@Component()        //默认bean名称为首字母小写的类名
public class ExpressionRoot {

    public boolean hasAuthority(String authority){
        //获取当前用户的权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();
        //判断用户权限集合中是否存在 authority
        return permissions.contains(authority);
    }

}
