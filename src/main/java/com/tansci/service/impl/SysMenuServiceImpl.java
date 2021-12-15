package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.common.constant.Enums;
import com.tansci.domain.SysDic;
import com.tansci.domain.SysMenu;
import com.tansci.domain.SysMenuRole;
import com.tansci.domain.SysUserRole;
import com.tansci.mapper.SysMenuMapper;
import com.tansci.service.SysDicService;
import com.tansci.service.SysMenuRoleService;
import com.tansci.service.SysMenuService;
import com.tansci.service.SysUserRoleService;
import com.tansci.utils.SecurityUserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName： SysMenuServiceImpl.java
 * @ClassPath： com.tansci.service.impl.SysMenuServiceImpl.java
 * @Description： 菜单
 * @Author： tanyp
 * @Date： 2021/7/20 17:05
 **/
@Slf4j
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysDicService sysDicService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysMenuRoleService sysMenuRoleService;

    @Override
    public IPage<SysMenu> page(Page page, SysMenu sysMenu) {
        IPage<SysMenu> menuIPage = this.page(page, Wrappers.<SysMenu>lambdaQuery()
                .eq(Objects.nonNull(sysMenu.getId()), SysMenu::getId, sysMenu.getId()).or()
                .eq(Objects.nonNull(sysMenu.getId()), SysMenu::getParentId, sysMenu.getId())
                .like(Objects.nonNull(sysMenu.getName()), SysMenu::getName, sysMenu.getName())
                .like(Objects.nonNull(sysMenu.getChineseName()), SysMenu::getChineseName, sysMenu.getChineseName())
                .like(Objects.nonNull(sysMenu.getEnglishName()), SysMenu::getEnglishName, sysMenu.getEnglishName())
        );

        List<SysDic> statusList = menuIPage.getSize() > 0 ? sysDicService.list(Wrappers.<SysDic>lambdaQuery().eq(SysDic::getGroupName, "menu_status")) : new ArrayList<>();
        menuIPage.getRecords().forEach(item -> {
            Optional<SysDic> sOptional = statusList.stream().filter(s -> s.getDicValue() == item.getStatus()).findFirst();
            if (sOptional.isPresent()) {
                item.setStatusName(sOptional.get().getDicLabel());
            }
            item.setTypeName(Enums.getVlaueByGroup(item.getType(), "menu_type"));
        });
        return menuIPage;
    }

    @Override
    public List<SysMenu> list(SysMenu sysMenu) {
        if (Objects.nonNull(sysMenu.getType()) && 1 == sysMenu.getType()) {
            SysUserRole role = sysUserRoleService.getOne(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, SecurityUserUtils.getUser().getId()));
            sysMenu.setRoleId(role.getRoleId());
        }
        List<SysMenu> list = this.baseMapper.list(sysMenu);
        if (Objects.nonNull(sysMenu.getRoleId())) {
            // 获取所有菜单的父idgit
            List<Integer> parentIds = list.stream().map(SysMenu::getParentId).distinct().collect(Collectors.toList());
            if (Objects.nonNull(parentIds) && parentIds.size() > 0) {
                List<SysMenu> parentList = this.list(
                        Wrappers.<SysMenu>lambdaQuery().in(SysMenu::getId, parentIds)
                                .eq(Objects.nonNull(sysMenu.getType()), SysMenu::getType, sysMenu.getType())
                                .eq(Objects.nonNull(sysMenu.getStatus()), SysMenu::getStatus, sysMenu.getStatus())
                );
                list.addAll(parentList);
            }
        }

        list = list.stream().distinct().collect(Collectors.toList());
        List<SysMenu> finalList = list;
        List<SysMenu> newMenuList = finalList.stream().filter(item -> item.getParentId() == 0).map(item -> {
            item.setChildren(this.getChildrens(item, finalList));
            return item;
        }).sorted((item1, item2) -> {
            return (item1.getSort() == null ? 0 : item1.getSort()) - (item2.getSort() == null ? 0 : item2.getSort());
        }).collect(Collectors.toList());
        return newMenuList;
    }

    /**
     * @MonthName： getChildrens
     * @Description： 树结构
     * @Author： tanyp
     * @Date： 2021/7/20 17:32
     * @Param： [menu, list]
     * @return： java.util.List<com.tanersci.domain.SysMenu>
     **/
    public List<SysMenu> getChildrens(SysMenu menu, List<SysMenu> list) {
        List<SysMenu> treeMenu = list.stream().filter(item -> Objects.equals(item.getParentId(), menu.getId())).map(item -> {
            // 递归添加子数据
            List<SysMenu> childrens = getChildrens(item, list);
            item.setChildren(childrens);
            return item;
        }).sorted((item1, item2) -> { // 排序
            return (item1.getSort() == null ? 0 : item1.getSort()) - (item2.getSort() == null ? 0 : item2.getSort());
        }).collect(Collectors.toList());
        return treeMenu;
    }

    @Transactional
    @Override
    public boolean save(SysMenu sysMenu) {
        sysMenu.setCreateTime(LocalDateTime.now());
        sysMenu.setStatus(1);
        if (Objects.isNull(sysMenu.getParentId())) {
            sysMenu.setParentId(0);
        }

        int row = this.baseMapper.insert(sysMenu);
        // 分配权限
        if (row > 0) {
            return sysMenuRoleService.save(
                    SysMenuRole.builder()
                            .menuId(sysMenu.getId())
                            .roleId(Integer.parseInt(SecurityUserUtils.getUser().getRole()))
                            .build()
            );
        }
        return false;
    }

    @Override
    public boolean del(Integer id) {
        List<SysMenu> menuList = this.baseMapper.getMenuChildrens(id);
        if (Objects.nonNull(menuList) && menuList.size() > 0) {
            List<Integer> ids = menuList.stream().map(SysMenu::getId).collect(Collectors.toList());
            ids.add(id);
            // 删除菜单
            this.baseMapper.deleteBatchIds(ids);
            // 删除权限
            return sysMenuRoleService.remove(Wrappers.<SysMenuRole>lambdaQuery().in(SysMenuRole::getMenuId, ids));
        }
        return false;
    }

}
