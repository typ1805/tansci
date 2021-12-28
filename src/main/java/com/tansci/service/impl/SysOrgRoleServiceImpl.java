package com.tansci.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.SysOrgRole;
import com.tansci.mapper.SysOrgRoleMapper;
import com.tansci.service.SysOrgRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @path：com.tansci.service.impl.SysOrgRoleServiceImpl.java
 * @className：SysOrgRoleServiceImpl.java
 * @description：组织角色
 * @author：tanyp
 * @dateTime：2021/10/23 13:45
 * @editNote：
 */
@Slf4j
@Service
public class SysOrgRoleServiceImpl extends ServiceImpl<SysOrgRoleMapper, SysOrgRole> implements SysOrgRoleService {
}
