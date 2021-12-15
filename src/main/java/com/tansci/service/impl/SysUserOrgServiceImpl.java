package com.tansci.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.SysUserOrg;
import com.tansci.mapper.SysUserOrgMapper;
import com.tansci.service.SysUserOrgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName： SysUserOrgServiceImpl.java
 * @ClassPath： com.tansci.service.impl.SysUserOrgServiceImpl.java
 * @Description： 用户组织
 * @Author： tanyp
 * @Date： 2021/12/15 10:15
 **/
@Slf4j
@Service
public class SysUserOrgServiceImpl extends ServiceImpl<SysUserOrgMapper, SysUserOrg> implements SysUserOrgService {
}
