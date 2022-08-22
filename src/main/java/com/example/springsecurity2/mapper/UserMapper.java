package com.example.springsecurity2.mapper;

import com.example.springsecurity2.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author author
 * @since 2022-08-20
 */
@Mapper
public interface UserMapper extends BaseMapper<SysUser> {

}
