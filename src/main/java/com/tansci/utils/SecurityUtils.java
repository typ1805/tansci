package com.tansci.utils;

import com.tansci.domain.system.SysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName： SecurityUtils.java
 * @ClassPath： com.tansci.utils.SecurityUtils.java
 * @Description： 用户登录信息
 * @Author： tanyp
 * @Date： 2021/12/02 17:26
 **/
@Data
public class SecurityUtils implements UserDetails {

    private String id;

    private String username;

    private String nickname;

    private String password;

    private Integer type;

    private Integer orgId;

    private List<Integer> orgIds;

    private Collection<? extends GrantedAuthority> authorities;

    public SecurityUtils() {
    }

    public SecurityUtils(SysUser user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.password = user.getPassword();
        this.type = user.getType();
        this.orgId = user.getOrgId();
        this.orgIds = user.getOrgIds();
        this.authorities = Collections.singleton(new SimpleGrantedAuthority(user.getRole().toString()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        // 账号是否未过期，默认是false
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 账号是否未锁定，默认是false
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 账号凭证是否未过期，默认是false
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
