package com.tansci.controller.system;

import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.domain.system.vo.AuthorizedVo;
import com.tansci.service.system.AuthorizedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * @ClassName： AuthorizedController.java
 * @ClassPath： com.tansci.controller.system.AuthorizedController.java
 * @Description： 三方授权
 * @Author： tanyp
 * @Date： 2022/3/1 10:18
 **/
@Slf4j
@RestController
@RequestMapping("/auth")
@Api(value = "auth", tags = "三方授权")
public class AuthorizedController {

    @Autowired
    private AuthorizedService authorizedService;

    @ApiOperation(value = "微信登录获取授权码", notes = "微信登录获取授权码")
    @PostMapping("/wxLogin")
    public Wrapper<AuthorizedVo> wxLogin() {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, authorizedService.wxLogin());
    }

    @ApiOperation(value = "微信登录授权回调", notes = "微信登录授权回调")
    @GetMapping("/wxCallback")
    public void wxCallback(String code, String state, HttpServletResponse response) throws IOException {
        authorizedService.wxCallback(code, state);
        PrintWriter out = response.getWriter();
        out.print("success");
        out.close();
    }

    @GetMapping("/wxCheck")
    public void wxCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Enumeration pNames = request.getParameterNames();
        while (pNames.hasMoreElements()) {
            String name = (String) pNames.nextElement();
        }
        String echostr = request.getParameter("echostr"); // 随机字符串
        PrintWriter out = response.getWriter();
        out.print(echostr);
        out.close();
    }

}
