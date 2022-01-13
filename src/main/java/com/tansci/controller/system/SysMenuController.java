package com.tansci.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.common.annotation.Log;
import com.tansci.common.constant.Constants;
import com.tansci.domain.system.SysMenu;
import com.tansci.service.system.SysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName： SysMenuController.java
 * @ClassPath： com.tansci.controller.SysMenuController.java
 * @Description： 菜单
 * @Author： tanyp
 * @Date： 2021/7/20 17:08
 **/
@Slf4j
@RestController
@RequestMapping("menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @Log(modul = "菜单-菜单分页", type = Constants.SELECT, desc = "菜单分页")
    @GetMapping("/page")
    public Wrapper<IPage<SysMenu>> page(Page page, SysMenu sysMenu) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysMenuService.page(page, sysMenu));
    }

    @Log(modul = "菜单-菜单列表", type = Constants.SELECT, desc = "菜单列表")
    @GetMapping("/list")
    public Wrapper<List<SysMenu>> list(SysMenu sysMenu) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysMenuService.list(sysMenu));
    }

    @Log(modul = "菜单-添加菜单", type = Constants.INSERT, desc = "添加菜单")
    @PostMapping("/save")
    public Wrapper<Boolean> save(@RequestBody SysMenu sysMenu) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysMenuService.save(sysMenu));
    }

    @Log(modul = "菜单-修改菜单", type = Constants.UPDATE, desc = "修改菜单")
    @PostMapping("/update")
    public Wrapper<Boolean> update(@RequestBody SysMenu sysMenu) {
        sysMenu.setUpdateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysMenuService.updateById(sysMenu));
    }

    @Log(modul = "菜单-删除菜单", type = Constants.DELETE, desc = "删除菜单")
    @GetMapping("/del")
    public Wrapper<Boolean> del(Integer id) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysMenuService.del(id));
    }

}
