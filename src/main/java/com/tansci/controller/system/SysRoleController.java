package com.tansci.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.common.annotation.Log;
import com.tansci.common.constant.Constants;
import com.tansci.domain.system.SysMenuRole;
import com.tansci.domain.system.SysOrgRole;
import com.tansci.domain.system.SysRole;
import com.tansci.domain.system.SysUserRole;
import com.tansci.domain.system.dto.SysRoleDto;
import com.tansci.domain.system.vo.SysMenuRoleVo;
import com.tansci.service.system.SysOrgRoleService;
import com.tansci.service.system.SysRoleService;
import com.tansci.service.system.SysUserRoleService;
import com.tansci.utils.SecurityUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName： SysRoleController.java
 * @ClassPath： com.tansci.controller.SysRoleController.java
 * @Description： 角色
 * @Author： tanyp
 * @Date： 2021/7/20 17:07
 **/
@Slf4j
@RestController
@RequestMapping("/role")
@Api(value = "role", tags = "角色")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysOrgRoleService sysOrgRoleService;

    @ApiOperation(value = "分页列表", notes = "分页列表")
    @Log(modul = "角色-分页列表", type = Constants.SELECT, desc = "分页列表")
    @GetMapping("/page")
    public Wrapper<IPage<SysRole>> page(Page page, SysRole sysRole) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.page(page, sysRole));
    }

    @ApiOperation(value = "列表", notes = "列表")
    @Log(modul = "角色-列表", type = Constants.SELECT, desc = "列表")
    @GetMapping("/list")
    public Wrapper<List<SysRole>> list(SysRole sysRole) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.list(sysRole));
    }

    @ApiOperation(value = "添加角色", notes = "添加角色")
    @Log(modul = "角色-添加角色", type = Constants.INSERT, desc = "添加角色")
    @PostMapping("/save")
    public Wrapper<Boolean> save(@RequestBody SysRole sysRole) {
        sysRole.setCreateTime(LocalDateTime.now());
        sysRole.setCreator(SecurityUserUtils.getUser().getId());
        sysRole.setStatus(1);
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.save(sysRole));
    }

    @ApiOperation(value = "修改角色", notes = "修改角色")
    @Log(modul = "角色-修改角色", type = Constants.UPDATE, desc = "修改角色")
    @PostMapping("/update")
    public Wrapper<Boolean> update(@RequestBody SysRole sysRole) {
        sysRole.setUpdateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.updateById(sysRole));
    }

    @ApiOperation(value = "删除角色", notes = "删除角色")
    @Log(modul = "角色-删除角色", type = Constants.DELETE, desc = "删除角色")
    @GetMapping("/del")
    public Wrapper<Boolean> del(Integer id) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.removeById(id));
    }

    @ApiOperation(value = "获取菜单角色", notes = "获取菜单角色")
    @Log(modul = "角色-获取菜单角色", type = Constants.SELECT, desc = "获取菜单角色")
    @GetMapping("/menuRoleList")
    public Wrapper<List<SysMenuRoleVo>> menuRoleList(SysMenuRole role) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.menuRoleList(role));
    }

    @ApiOperation(value = "分页列表", notes = "分页列表")
    @Log(modul = "角色-分页列表", type = Constants.SELECT, desc = "分页列表")
    @PostMapping("/menuRoleAdd")
    public Wrapper<Boolean> menuRoleAdd(@RequestBody SysRoleDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.menuRoleAdd(dto));
    }

    @ApiOperation(value = "获取用户角色信息", notes = "获取用户角色信息")
    @Log(modul = "角色-获取用户角色信息", type = Constants.SELECT, desc = "获取用户角色信息")
    @GetMapping("/userRoleInfo")
    public Wrapper<SysUserRole> userRoleInfo(SysUserRole role) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE,
                sysUserRoleService.getOne(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, role.getUserId()))
        );
    }

    @ApiOperation(value = "添加用户角色", notes = "添加用户角色")
    @Log(modul = "角色-添加用户角色", type = Constants.INSERT, desc = "添加用户角色")
    @PostMapping("/userRoleAdd")
    public Wrapper<Boolean> userRoleAdd(@RequestBody SysRoleDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.userRoleAdd(dto));
    }

    @ApiOperation(value = "获取角色信息", notes = "获取角色信息")
    @Log(modul = "角色-获取角色信息", type = Constants.SELECT, desc = "获取角色信息")
    @GetMapping("/orgRoleInfo")
    public Wrapper<SysOrgRole> orgRoleInfo(SysOrgRole role) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE,
                sysOrgRoleService.getOne(Wrappers.<SysOrgRole>lambdaQuery().eq(SysOrgRole::getOrgId, role.getOrgId()))
        );
    }

    @ApiOperation(value = "添加组织权限", notes = "添加组织权限")
    @Log(modul = "角色-添加组织权限", type = Constants.INSERT, desc = "添加组织权限")
    @PostMapping("/orgRoleAdd")
    public Wrapper<Boolean> orgRoleAdd(@RequestBody SysRoleDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.orgRoleAdd(dto));
    }

}
