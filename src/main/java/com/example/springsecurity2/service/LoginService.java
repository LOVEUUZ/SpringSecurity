package com.example.springsecurity2.service;

import com.example.springsecurity2.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springsecurity2.result.Result;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author author
 * @since 2022-08-20
 */
public interface LoginService extends IService<SysUser> {

    Result<Map<String, String>> login(SysUser user);

    Result logout();
}
