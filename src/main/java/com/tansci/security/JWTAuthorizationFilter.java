package com.tansci.security;

import com.alibaba.fastjson.JSONObject;
import com.tansci.common.WrapMapper;
import com.tansci.common.constant.Enums;
import com.tansci.common.exception.BusinessException;
import com.tansci.domain.system.SysUser;
import com.tansci.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * @ClassName： JWTAuthorizationFilter.java
 * @ClassPath： com.tansci.security.JWTAuthorizationFilter.java
 * @Description： 配置拦截器, 用户权限验证
 * @Author： tanyp
 * @Date： 2021/10/22 17:55
 **/
@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
        if (token == null || !token.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        try {
            Authentication authentication = getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            onSuccessfulAuthentication(request, response, authentication);
            chain.doFilter(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            onUnsuccessfulAuthentication(request, response, new AccountExpiredException(Enums.AUTH_NO_TOKEN.getValue()));
        }
    }

    @Override
    protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException {
        log.info("=============Token 验证成功=================");
    }

    @Override
    protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        log.error("================token校验失败=======================");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSONObject.toJSONString(WrapMapper.error(HttpServletResponse.SC_UNAUTHORIZED, failed.getMessage())));
    }


    /**
     * @MonthName： getAuthentication
     * @Description： 从token中获取用户信息并新建一个token
     * @Author： tanyp
     * @Date： 2021/10/22 17:55
     * @Param： [tokenHeader]
     * @return： org.springframework.security.authentication.UsernamePasswordAuthenticationToken
     **/
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        boolean expiration = JwtTokenUtils.isExpiration(token);
        if (expiration) {
            throw new BusinessException(Enums.AUTH_NO_TOKEN.getValue());
        } else {
            String username = JwtTokenUtils.getUsername(token);
            String role = JwtTokenUtils.getUserRole(token);
            SysUser user = JwtTokenUtils.getUser(token);
            if (username != null) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, Collections.singleton(new SimpleGrantedAuthority(role)));
                authenticationToken.setDetails(user);
                return authenticationToken;
            }
        }
        return null;
    }

}
