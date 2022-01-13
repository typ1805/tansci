package com.tansci.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.domain.system.SysMenuRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName： SysMenuRoleMapper.java
 * @ClassPath： com.tansci.mapper.SysMenuRoleMapper.java
 * @Description： 菜单角色
 * @Author： tanyp
 * @Date： 2021/7/20 17:00
 **/
@Mapper
public interface SysMenuRoleMapper extends BaseMapper<SysMenuRole> {
}
