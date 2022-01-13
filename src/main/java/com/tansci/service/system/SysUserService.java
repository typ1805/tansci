package com.tansci.service.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.system.SysUser;
import com.tansci.domain.system.dto.SysUserDto;

import java.util.List;

/**
 * @path：com.tansci.service.SysUserService.java
 * @className：SysUserService.java
 * @description：用户
 * @author：tanyp
 * @dateTime：2021/6/19 22:16
 * @editNote：
 */
public interface SysUserService extends IService<SysUser> {

    IPage<SysUser> page(Page page, SysUserDto dto);

    List<SysUser> list(SysUserDto dto);

    Integer modifyPass(SysUserDto dto);

    boolean save(SysUser user);

    boolean update(SysUser user);

    boolean del(SysUserDto dto);

    SysUser login(String username);

}
