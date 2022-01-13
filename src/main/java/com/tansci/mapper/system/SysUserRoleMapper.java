package com.tansci.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.domain.system.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName： SysUserRoleMapper.java
 * @ClassPath： com.tansci.mapper.SysUserRoleMapper.java
 * @Description： 用户角色
 * @Author： tanyp
 * @Date： 2021/7/20 16:59
 **/
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
}
