package com.tansci.service.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.system.SysMenu;

import java.util.List;

/**
 * @ClassName： SysMenuService.java
 * @ClassPath： com.tansci.service.SysMenuService.java
 * @Description： 菜单
 * @Author： tanyp
 * @Date： 2021/7/20 17:01
 **/
public interface SysMenuService extends IService<SysMenu> {

    IPage<SysMenu> page(Page page, SysMenu sysMenu);

    List<SysMenu> list(SysMenu sysMenu);

    boolean save(SysMenu sysMenu);

    boolean del(Integer id);
}
