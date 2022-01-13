package com.tansci.security;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.common.constant.Enums;
import com.tansci.domain.system.SysUser;
import com.tansci.domain.system.vo.SysUserVo;
import com.tansci.utils.JwtTokenUtils;
import com.tansci.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * @ClassName： JWTAuthenticationFilter.java
 * @ClassPath： com.tansci.security.JWTAuthenticationFilter.java
 * @Description： 配置拦截器, 用户账号验证
 * @Author： tanyp
 * @Date： 2021/10/22 17:46
 **/
@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        /**
         * 默认登录是 ‘/login’
         */
        super.setFilterProcessesUrl("/user/login");
    }

    /**
     * @MonthName： attemptAuthentication
     * @Description： 获取登录信息
     * @Author： tanyp
     * @Date： 2021/10/22 17:46
     * @Param： [request, response]
     * @return： org.springframework.security.core.Authentication
     **/
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        SysUser user = null;
        try {
            user = new ObjectMapper().readValue(request.getInputStream(), SysUser.class);
        } catch (IOException e) {
            log.error("=======获取登录信息异常：{}", e);
        }
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    }

    /**
     * @MonthName： successfulAuthentication
     * @Description： 成功验证后调用的方法
     * 如果验证成功，就生成token并返回
     * @Author： tanyp
     * @Date： 2021/10/22 17:46
     * @Param： [request, response, chain, authResult]
     * @return： void
     **/
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        SecurityUtils user = (SecurityUtils) authResult.getPrincipal();
        String role = "";
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            role = authority.getAuthority();
        }

        // 创建token
        String token = JwtTokenUtils.createToken(
                SysUser.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .type(user.getType())
                        .orgId(user.getOrgId())
                        .orgIds(user.getOrgIds())
                        .role(role)
                        .build(), false);

        // 创建成功的token, 请求的格式应该是 `Bearer token`
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSONObject.toJSONString(WrapMapper.success().result(
                SysUserVo.builder()
                        .username(user.getUsername())
                        .nickname(user.getNickname())
                        .token(token)
                        .loginTime(LocalDateTime.now()).build()
        )));
    }

    /**
     * @MonthName： unsuccessfulAuthentication
     * @Description： 这是验证失败时候调用的方法
     * @Author： tanyp
     * @Date： 2021/10/22 17:46
     * @Param： [request, response, failed]
     * @return： void
     **/
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        log.info("===========登录认证失败：{}=============", failed.getMessage());
        Wrapper result = null;
        if (failed instanceof UsernameNotFoundException) {
            result = WrapMapper.error(Enums.AUTH_NONEXISTENT.getKey(), Enums.AUTH_NONEXISTENT.getValue());
        } else if (failed instanceof BadCredentialsException) {
            result = WrapMapper.error(Enums.AUTH_NO_TOKEN.getKey(), Enums.AUTH_NO_TOKEN.getValue());
        } else if (failed instanceof InternalAuthenticationServiceException) {
            String message = failed.getMessage() != null ? failed.getMessage() : Wrapper.ERROR_MESSAGE;
            result = WrapMapper.error(Enums.AUTH_NO_ACCESS.getKey(), message);
        }
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSONObject.toJSONString(result));
    }

}
