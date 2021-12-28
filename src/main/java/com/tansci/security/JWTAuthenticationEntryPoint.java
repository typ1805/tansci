package com.tansci.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName： JWTAuthenticationEntryPoint.java
 * @ClassPath： com.tansci.security.JWTAuthenticationEntryPoint.java
 * @Description： 没有携带token或者token无效
 * @Author： tanyp
 * @Date： 2021/10/22 17:55
 **/
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(new ObjectMapper().writeValueAsString(WrapMapper.wrap(Wrapper.AUTHORIZATION_CODE, Wrapper.AUTHORIZATION_MESSAGE, null)));
    }

}
