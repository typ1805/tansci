package com.tansci.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.domain.system.SysUser;
import com.tansci.domain.system.dto.SysUserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    Page<SysUser> page(Page page, @Param("dto")SysUserDto dto);
}
