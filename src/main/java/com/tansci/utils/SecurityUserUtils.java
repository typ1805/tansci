package com.tansci.utils;

import com.tansci.domain.system.SysUser;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @ClassName： SecurityUtils.java
 * @ClassPath： com.kuiper.tansci.utils.SecurityUtils.java
 * @Description： 用户登录信息
 * @Author： tanyp
 * @Date： 2021/12/15 10:26
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUserUtils {

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static SysUser getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (SysUser) authentication.getDetails();
    }

    public static void logout() {
        SecurityContextHolder.clearContext();
    }

}
