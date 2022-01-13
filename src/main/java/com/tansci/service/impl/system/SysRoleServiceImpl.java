package com.tansci.service.impl.system;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.common.constant.Enums;
import com.tansci.domain.system.dto.SysRoleDto;
import com.tansci.domain.system.*;
import com.tansci.domain.system.vo.SysMenuRoleVo;
import com.tansci.mapper.system.SysRoleMapper;
import com.tansci.service.system.*;
import com.tansci.utils.SecurityUserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName： SysRoleServiceImpl.java
 * @ClassPath： com.tansci.service.impl.SysRoleServiceImpl.java
 * @Description： 角色
 * @Author： tanyp
 * @Date： 2021/7/20 17:06
 **/
@Slf4j
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysDicService sysDicService;
    @Autowired
    private SysMenuRoleService sysMenuRoleService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysOrgRoleService sysOrgRoleService;
    @Autowired
    private SysUserOrgService sysUserOrgService;
    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public IPage<SysRole> page(Page page, SysRole role) {
        LambdaQueryWrapper queryWrapper = null;
        if (Objects.equals(0, SecurityUserUtils.getUser().getType())) {
            queryWrapper = Wrappers.<SysRole>lambdaQuery()
                    .like(Objects.nonNull(role.getName()), SysRole::getName, role.getName())
                    .orderByDesc(SysRole::getCreateTime, SysRole::getUpdateTime);
        } else {
            List<SysUserOrg> orgs = sysUserOrgService.list(Wrappers.<SysUserOrg>lambdaQuery().in(SysUserOrg::getOrgId, SecurityUserUtils.getUser().getOrgIds()));
            List<String> userIds = new ArrayList<>();
            if (Objects.nonNull(orgs) && orgs.size() > 0) {
                userIds = orgs.stream().map(SysUserOrg::getUserId).distinct().collect(Collectors.toList());
            } else {
                userIds.add(SecurityUserUtils.getUser().getId());
            }
            queryWrapper = Wrappers.<SysRole>lambdaQuery()
                    .in(SysRole::getCreator, userIds)
                    .like(Objects.nonNull(role.getName()), SysRole::getName, role.getName())
                    .orderByDesc(SysRole::getCreateTime, SysRole::getUpdateTime);
        }
        IPage<SysRole> roleIPage = this.baseMapper.selectPage(page, queryWrapper);

        List<SysDic> statusList = roleIPage.getSize() > 0 ? sysDicService.list(Wrappers.<SysDic>lambdaQuery().eq(SysDic::getGroupName, "role_status")) : new ArrayList<>();
        roleIPage.getRecords().forEach(item -> {
            Optional<SysDic> sOptional = statusList.stream().filter(s -> s.getDicValue() == item.getStatus()).findFirst();
            if (sOptional.isPresent()) {
                item.setStatusName(sOptional.get().getDicLabel());
            }
            item.setTypeName(Enums.getVlaueByGroup(item.getType(), "role_type"));
        });
        return roleIPage;
    }

    @Override
    public List<SysRole> list(SysRole role) {
        LambdaQueryWrapper queryWrapper = null;
        if (Objects.equals(0, SecurityUserUtils.getUser().getType())) {
            queryWrapper = Wrappers.<SysRole>lambdaQuery()
                    .like(Objects.nonNull(role.getName()), SysRole::getName, role.getName())
                    .orderByDesc(SysRole::getCreateTime, SysRole::getUpdateTime);
        } else {
            List<SysUserOrg> orgs = sysUserOrgService.list(Wrappers.<SysUserOrg>lambdaQuery().in(SysUserOrg::getOrgId, SecurityUserUtils.getUser().getOrgIds()));
            List<String> userIds = new ArrayList<>();
            if (Objects.nonNull(orgs) && orgs.size() > 0) {
                userIds = orgs.stream().map(SysUserOrg::getUserId).distinct().collect(Collectors.toList());
            } else {
                userIds.add(SecurityUserUtils.getUser().getId());
            }
            queryWrapper = Wrappers.<SysRole>lambdaQuery()
                    .in(SysRole::getCreator, userIds)
                    .like(Objects.nonNull(role.getName()), SysRole::getName, role.getName())
                    .orderByDesc(SysRole::getCreateTime, SysRole::getUpdateTime);
        }
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<SysMenuRoleVo> menuRoleList(SysMenuRole role) {
        List<SysMenuRoleVo> roleVos = new ArrayList<>();
        if (!Objects.equals(0, SecurityUserUtils.getUser().getType())) {
            role.setThisRoleId(Integer.parseInt(SecurityUserUtils.getUser().getRole()));
            roleVos = this.baseMapper.menuRoleList(role);

            if (Objects.nonNull(roleVos)) {
                // 获取所有菜单的父id
                List<Integer> parentIds = roleVos.stream().map(SysMenuRoleVo::getParentId).distinct().collect(Collectors.toList());
                if (Objects.nonNull(parentIds) && parentIds.size() > 0) {
                    List<SysMenu> parentList = sysMenuService.list(Wrappers.<SysMenu>lambdaQuery().in(SysMenu::getId, parentIds));
                    List<SysMenuRoleVo> menuRoleVos = new ArrayList<>();
                    parentList.forEach(item -> {
                        menuRoleVos.add(
                                SysMenuRoleVo.builder()
                                        .id(item.getId())
                                        .parentId(item.getParentId())
                                        .name(item.getName())
                                        .chineseName(item.getChineseName())
                                        .sort(item.getSort())
                                        .roleId(null)
                                        .menuId(null)
                                        .build()
                        );
                    });
                    roleVos.addAll(menuRoleVos);
                }
            }
        } else {
            roleVos = this.baseMapper.menuRoleList(role);
        }

        roleVos = roleVos.stream().distinct().collect(Collectors.toList());
        Map<Integer, List<SysMenuRoleVo>> map = roleVos.stream().collect(Collectors.groupingBy(SysMenuRoleVo::getParentId, Collectors.toList()));
        roleVos.stream().forEach(item -> item.setChildren(map.get(item.getId())));
        return map.get(0);
    }

    @Transient
    @Override
    public Boolean menuRoleAdd(SysRoleDto dto) {
        sysMenuRoleService.remove(Wrappers.<SysMenuRole>lambdaQuery().eq(SysMenuRole::getRoleId, dto.getRoleId()));
        List<SysMenuRole> roles = new ArrayList<>();
        dto.getMenuIds().forEach(id -> {
            roles.add(SysMenuRole.builder().roleId(dto.getRoleId()).menuId(id).build());
        });
        return sysMenuRoleService.saveBatch(roles);
    }

    @Override
    public Boolean userRoleAdd(SysRoleDto dto) {
        sysUserRoleService.remove(Wrappers.<SysUserRole>lambdaUpdate().eq(SysUserRole::getUserId, dto.getUserId()));
        return sysUserRoleService.save(SysUserRole.builder().roleId(dto.getRoleId()).userId(dto.getUserId()).build());
    }

    @Override
    public Boolean orgRoleAdd(SysRoleDto dto) {
        sysOrgRoleService.remove(Wrappers.<SysOrgRole>lambdaUpdate().eq(SysOrgRole::getOrgId, dto.getUserId()));
        return sysOrgRoleService.save(SysOrgRole.builder().roleId(dto.getRoleId()).orgId(dto.getOrgId()).build());
    }

}
