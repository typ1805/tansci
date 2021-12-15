package com.tansci.security;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tansci.common.exception.BusinessException;
import com.tansci.domain.SysUser;
import com.tansci.domain.SysUserRole;
import com.tansci.service.SysUserRoleService;
import com.tansci.service.SysUserService;
import com.tansci.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @ClassName： UserDetailsServiceImpl.java
 * @ClassPath： com.tansci.security.UserDetailsServiceImpl.java
 * @Description： 用户认证
 * @Author： tanyp
 * @Date： 2021/10/22 17:30
 **/
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        SysUser user = sysUserService.getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, username));
        if (Objects.isNull(user)) {
            throw new BusinessException("用户名获取密码有误！");
        }

        // 获取角色
        SysUserRole role = sysUserRoleService.getOne(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, user.getId()));
        if (Objects.isNull(role) || Objects.isNull(role.getRoleId())) {
            throw new BusinessException("暂无登录权限，请联系管理员！");
        }
        user.setRole(String.valueOf(role.getRoleId()));
        return new SecurityUtils(user);
    }

}
