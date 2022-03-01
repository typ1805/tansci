package com.tansci.controller.system;

import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.common.annotation.Log;
import com.tansci.common.constant.Constants;
import com.tansci.service.system.AuthorizedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @Log(modul = "三方授权-微信登录获取授权码", type = Constants.SELECT, desc = "微信登录获取授权码")
    @GetMapping("/wxLogin")
    public Wrapper<Object> wxLogin() {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, authorizedService.wxLogin());
    }

}
