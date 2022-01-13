package com.tansci.service.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.system.SysMenuRole;
import com.tansci.domain.system.SysRole;
import com.tansci.domain.system.dto.SysRoleDto;
import com.tansci.domain.system.vo.SysMenuRoleVo;

import java.util.List;

/**
 * @ClassName： SysRoleService.java
 * @ClassPath： com.tansci.service.SysRoleService.java
 * @Description： 角色
 * @Author： tanyp
 * @Date： 2021/7/20 17:01
 **/
public interface SysRoleService extends IService<SysRole> {

    IPage<SysRole> page(Page page, SysRole sysMenu);

    List<SysRole> list(SysRole sysMenu);

    List<SysMenuRoleVo> menuRoleList(SysMenuRole role);

    Boolean menuRoleAdd(SysRoleDto dto);

    Boolean userRoleAdd(SysRoleDto dto);

    Boolean orgRoleAdd(SysRoleDto dto);

}
