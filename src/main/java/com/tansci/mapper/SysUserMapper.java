package com.tansci.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @path：com.tansci.mapper.SysUserMapper.java
 * @className：SysUserMapper.java
 * @description：用户表
 * @author：tanyp
 * @dateTime：2021/6/19 22:01
 * @editNote：
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

}
