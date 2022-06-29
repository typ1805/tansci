package com.tansci.service.impl.system;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.system.SysMenu;
import com.tansci.domain.system.SysMenuRole;
import com.tansci.domain.system.SysUserRole;
import com.tansci.domain.system.dto.SysMenuDto;
import com.tansci.mapper.system.SysMenuMapper;
import com.tansci.service.system.SysDicService;
import com.tansci.service.system.SysMenuRoleService;
import com.tansci.service.system.SysMenuService;
import com.tansci.service.system.SysUserRoleService;
import com.tansci.utils.SecurityUserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
    public List<SysMenu> list(SysMenuDto dto) {
        if (Objects.nonNull(SecurityUserUtils.getUser().getType()) && Objects.equals(1, SecurityUserUtils.getUser().getType())) {
            SysUserRole role = sysUserRoleService.getOne(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, SecurityUserUtils.getUser().getId()));
            dto.setRoleId(role.getRoleId());
        }
        List<SysMenu> list = this.baseMapper.list(dto);
        if (Objects.nonNull(dto.getRoleId())) {
            // 获取所有菜单的父idgit
            List<Integer> parentIds = list.stream().map(SysMenu::getParentId).distinct().collect(Collectors.toList());
            if (Objects.nonNull(parentIds) && parentIds.size() > 0) {
                List<SysMenu> parentList = this.list(
                        Wrappers.<SysMenu>lambdaQuery().in(SysMenu::getId, parentIds)
                                .in(Objects.nonNull(dto.getTypes()), SysMenu::getType, dto.getTypes())
                                .eq(Objects.nonNull(dto.getStatus()), SysMenu::getStatus, dto.getStatus())
                );
                list.addAll(parentList);
            }
        }

        list = list.stream().distinct().collect(Collectors.toList());
        Map<Integer, List<SysMenu>> map = list.stream().collect(Collectors.groupingBy(SysMenu::getParentId, Collectors.toList()));
        list.stream().forEach(item -> item.setChildren(map.get(item.getId())));
        return map.get(0);
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

    @Transactional
    @Override
    public boolean del(Integer id) {
        List<Integer> ids = new ArrayList<>();
        ids.add(id);
        List<SysMenu> menuList = this.baseMapper.getMenuChildrens(id);
        if (Objects.nonNull(menuList) && menuList.size() > 0) {
            ids.addAll(menuList.stream().map(SysMenu::getId).collect(Collectors.toList()));
        }
        // 删除菜单
        this.baseMapper.deleteBatchIds(ids);
        // 删除权限
        return sysMenuRoleService.remove(Wrappers.<SysMenuRole>lambdaQuery().in(SysMenuRole::getMenuId, ids));
    }

}
