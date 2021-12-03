package com.tansci.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName： JWTAccessDeniedHandler.java
 * @ClassPath： com.tansci.security.JWTAccessDeniedHandler.java
 * @Description： 没有访问权限
 * @Author： tanyp
 * @Date： 2021/10/22 17:52
 **/
public class JWTAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(new ObjectMapper().writeValueAsString(WrapMapper.wrap(Wrapper.AUTHORIZATION_CODE, Wrapper.AUTHORIZATION_MESSAGE, null)));
    }

}
