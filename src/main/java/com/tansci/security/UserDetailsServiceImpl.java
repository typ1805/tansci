package com.tansci.security;

import com.tansci.common.exception.BusinessException;
import com.tansci.domain.system.SysUser;
import com.tansci.service.system.SysUserService;
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

    @Override
    public UserDetails loadUserByUsername(String username) {
        SysUser user = sysUserService.login(username);
        if (Objects.isNull(user)) {
            throw new BusinessException("用户名获取密码有误！");
        }
        return new SecurityUtils(user);
    }

}
