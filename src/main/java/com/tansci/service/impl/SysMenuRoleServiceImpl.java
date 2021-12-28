package com.tansci.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.SysMenuRole;
import com.tansci.mapper.SysMenuRoleMapper;
import com.tansci.service.SysMenuRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName： SysMenuRoleServiceImpl.java
 * @ClassPath： com.tansci.service.impl.SysMenuRoleServiceImpl.java
 * @Description： 菜单角色
 * @Author： tanyp
 * @Date： 2021/7/20 17:03
 **/
@Slf4j
@Service
public class SysMenuRoleServiceImpl extends ServiceImpl<SysMenuRoleMapper, SysMenuRole> implements SysMenuRoleService {
}
