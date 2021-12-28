package com.tansci.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.common.annotation.Log;
import com.tansci.common.constant.Constants;
import com.tansci.domain.SysMenuRole;
import com.tansci.domain.SysOrgRole;
import com.tansci.domain.SysRole;
import com.tansci.domain.SysUserRole;
import com.tansci.domain.dto.SysRoleDto;
import com.tansci.domain.vo.SysMenuRoleVo;
import com.tansci.service.SysOrgRoleService;
import com.tansci.service.SysRoleService;
import com.tansci.service.SysUserRoleService;
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
@RequestMapping("role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysOrgRoleService sysOrgRoleService;

    @Log(modul = "角色-分页列表", type = Constants.SELECT, desc = "分页列表")
    @GetMapping("/page")
    public Wrapper<IPage<SysRole>> page(Page page, SysRole sysRole) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.page(page, sysRole));
    }

    @Log(modul = "角色-列表", type = Constants.SELECT, desc = "列表")
    @GetMapping("/list")
    public Wrapper<List<SysRole>> list(SysRole sysRole) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.list(sysRole));
    }

    @Log(modul = "角色-添加角色", type = Constants.INSERT, desc = "添加角色")
    @PostMapping("/save")
    public Wrapper<Boolean> save(@RequestBody SysRole sysRole) {
        sysRole.setCreateTime(LocalDateTime.now());
        sysRole.setStatus(1);
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.save(sysRole));
    }

    @Log(modul = "角色-修改角色", type = Constants.UPDATE, desc = "修改角色")
    @PostMapping("/update")
    public Wrapper<Boolean> update(@RequestBody SysRole sysRole) {
        sysRole.setUpdateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.updateById(sysRole));
    }

    @Log(modul = "角色-删除角色", type = Constants.DELETE, desc = "删除角色")
    @GetMapping("/del")
    public Wrapper<Boolean> del(Integer id) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.removeById(id));
    }

    @Log(modul = "角色-获取菜单角色", type = Constants.SELECT, desc = "获取菜单角色")
    @GetMapping("/menuRoleList")
    public Wrapper<List<SysMenuRoleVo>> menuRoleList(SysMenuRole role) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.menuRoleList(role));
    }

    @Log(modul = "角色-分页列表", type = Constants.SELECT, desc = "分页列表")
    @PostMapping("/menuRoleAdd")
    public Wrapper<Boolean> menuRoleAdd(@RequestBody SysRoleDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.menuRoleAdd(dto));
    }

    @Log(modul = "角色-获取用户角色信息", type = Constants.SELECT, desc = "获取用户角色信息")
    @GetMapping("/userRoleInfo")
    public Wrapper<SysUserRole> userRoleInfo(SysUserRole role) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE,
                sysUserRoleService.getOne(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, role.getUserId()))
        );
    }

    @Log(modul = "角色-添加用户角色", type = Constants.INSERT, desc = "添加用户角色")
    @PostMapping("/userRoleAdd")
    public Wrapper<Boolean> userRoleAdd(@RequestBody SysRoleDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.userRoleAdd(dto));
    }

    @Log(modul = "角色-获取角色信息", type = Constants.SELECT, desc = "获取角色信息")
    @GetMapping("/orgRoleInfo")
    public Wrapper<SysOrgRole> orgRoleInfo(SysOrgRole role) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE,
                sysOrgRoleService.getOne(Wrappers.<SysOrgRole>lambdaQuery().eq(SysOrgRole::getOrgId, role.getOrgId()))
        );
    }

    @Log(modul = "角色-添加组织权限", type = Constants.INSERT, desc = "添加组织权限")
    @PostMapping("/orgRoleAdd")
    public Wrapper<Boolean> orgRoleAdd(@RequestBody SysRoleDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysRoleService.orgRoleAdd(dto));
    }

}
