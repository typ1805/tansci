package com.tansci.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName： JWTLogoutSuccessHandler.java
 * @ClassPath： com.tansci.security.JWTLogoutSuccessHandler.java
 * @Description： 登出处理
 * @Author： tanyp
 * @Date： 2021/10/22 19:38
 **/
@Slf4j
public class JWTLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("========退出成功===========");
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(new ObjectMapper().writeValueAsString(WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, null)));
    }

}
