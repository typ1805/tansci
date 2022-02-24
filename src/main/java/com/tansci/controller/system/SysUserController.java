package com.tansci.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.common.annotation.Log;
import com.tansci.common.constant.Constants;
import com.tansci.domain.system.SysUser;
import com.tansci.domain.system.dto.SysUserDto;
import com.tansci.service.system.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @path：com.tansci.controller.SysUserController.java
 * @className：SysUserController.java
 * @description：用户
 * @author：tanyp
 * @dateTime：2021/6/19 22:18
 * @editNote：
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Api(value = "user", tags = "用户")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "用户分页", notes = "用户分页")
    @Log(modul = "用户-用户分页", type = Constants.SELECT, desc = "用户分页")
    @GetMapping("/page")
    public Wrapper<IPage<SysUser>> page(Page page, SysUserDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserService.page(page, dto));
    }

    @ApiOperation(value = "用户列表", notes = "用户列表")
    @Log(modul = "用户-用户列表", type = Constants.SELECT, desc = "用户列表")
    @GetMapping("/list")
    public Wrapper<List<SysUser>> list(SysUserDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserService.list(dto));
    }

    @ApiOperation(value = "添加用户信息", notes = "添加用户信息")
    @Log(modul = "用户-添加用户信息", type = Constants.INSERT, desc = "添加用户信息")
    @PostMapping("/save")
    public Wrapper<Boolean> save(@RequestBody SysUser user) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserService.save(user));
    }

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @Log(modul = "用户-修改用户信息", type = Constants.UPDATE, desc = "修改用户信息")
    @PostMapping("/update")
    public Wrapper<Boolean> update(@RequestBody SysUser user) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserService.update(user));
    }

    @ApiOperation(value = "删除用户", notes = "删除用户")
    @Log(modul = "用户-删除用户", type = Constants.DELETE, desc = "删除用户")
    @GetMapping("/del")
    public Wrapper<Boolean> del(SysUserDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserService.del(dto));
    }

    @ApiOperation(value = "修改密码", notes = "修改密码")
    @Log(modul = "用户-修改密码", type = Constants.UPDATE, desc = "修改密码")
    @PostMapping("/modifyPass")
    public Wrapper<Integer> modifyPass(@RequestBody SysUserDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysUserService.modifyPass(dto));
    }

}
