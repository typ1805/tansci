package com.tansci.service.impl.system;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.common.constant.Constants;
import com.tansci.common.constant.Enums;
import com.tansci.common.exception.BusinessException;
import com.tansci.domain.system.*;
import com.tansci.domain.system.dto.SysUserDto;
import com.tansci.mapper.system.SysUserMapper;
import com.tansci.service.system.*;
import com.tansci.utils.SecurityUserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @path：com.tansci.service.impl.SysUserServiceImpl.java
 * @className：SysUserServiceImpl.java
 * @description：用户
 * @author：tanyp
 * @dateTime：2021/6/19 22:17
 * @editNote：
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserOrgService sysUserOrgService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysOrgService sysOrgService;
    @Autowired
    private SysDicService sysDicService;

    @Override
    public IPage<SysUser> page(Page page, SysUserDto dto) {
        if (!Objects.equals(0, SecurityUserUtils.getUser().getType())) {
            dto.setOrgIds(SecurityUserUtils.getUser().getOrgIds());
        }
        Page<SysUser> sysUserPage = this.baseMapper.page(page, dto);

        List<SysDic> typeList = sysUserPage.getSize() > 0 ? sysDicService.list(Wrappers.<SysDic>lambdaQuery().eq(SysDic::getGroupName, "user_type")) : new ArrayList<>();
        sysUserPage.getRecords().forEach(item -> {
            Optional<SysDic> sOptional = typeList.stream().filter(s -> s.getDicValue() == item.getType()).findFirst();
            if (sOptional.isPresent()) {
                item.setTypeName(sOptional.get().getDicLabel());
            }
            item.setGenderName(Enums.getVlaueByGroup(item.getGender(), "user_gender"));
            item.setDelFlagName(Enums.getVlaueByGroup(item.getDelFlag(), "del_falg"));
        });
        return sysUserPage;
    }

    @Override
    public List<SysUser> list(SysUserDto dto) {
        LambdaQueryWrapper queryWrapper = null;
        if (Objects.equals(0, SecurityUserUtils.getUser().getType())) {
            queryWrapper = Wrappers.<SysUser>lambdaQuery()
                    .eq(SysUser::getDelFlag, 0)
                    .like(Objects.nonNull(dto.getNickname()), SysUser::getNickname, dto.getNickname())
                    .orderByDesc(SysUser::getCreateTime, SysUser::getUpdateTime);
        } else {
            queryWrapper = Wrappers.<SysUser>lambdaQuery()
                    .eq(SysUser::getDelFlag, 0)
                    .in(SysUser::getOrgId, SecurityUserUtils.getUser().getOrgIds())
                    .like(Objects.nonNull(dto.getNickname()), SysUser::getNickname, dto.getNickname())
                    .orderByDesc(SysUser::getCreateTime, SysUser::getUpdateTime);
        }
        return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public SysUser login(String username) {
        SysUser user = this.baseMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, username));
        if (Objects.nonNull(user)) {
            // 获取角色
            SysUserRole role = sysUserRoleService.getOne(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, user.getId()));
            log.error("====={}=======无权限================", username);
            if (Objects.isNull(role) || Objects.isNull(role.getRoleId())) {
                throw new BusinessException("暂无登录权限，请联系管理员！");
            }
            user.setRole(String.valueOf(role.getRoleId()));

            // 获取组织
            SysUserOrg org = sysUserOrgService.getOne(Wrappers.<SysUserOrg>lambdaQuery().eq(SysUserOrg::getUserId, user.getId()));
            if (Objects.isNull(org) || Objects.isNull(org.getOrgId())) {
                log.error("====={}=======无组织================", username);
                throw new BusinessException("暂无登录权限，请联系管理员！");
            }

            // 获取组织权限
            List<SysOrg> orgs = sysOrgService.getOrgChildrens(org.getOrgId());
            if (Objects.nonNull(orgs) && orgs.size() > 0) {
                List<Integer> orgIds = orgs.stream().map(SysOrg::getId).collect(Collectors.toList());
                orgIds.add(org.getOrgId());
                user.setOrgIds(orgIds);
            } else {
                user.setOrgIds(Arrays.asList(org.getOrgId()));
            }
        }
        return user;
    }

    /**
     * @MonthName： modifyPass
     * @Description： 修改密码
     * @Author： tanyp
     * @Date： 2021/7/8 16:37
     * @Param： [dto]
     * @return： java.lang.Integer
     **/
    @Override
    public Integer modifyPass(SysUserDto dto) {
        SysUser user = this.baseMapper.selectOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, dto.getUsername()));
        // 验证原始密码
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Boolean flag = passwordEncoder.matches(dto.getOldPassword(), user.getPassword());
        if (Objects.isNull(user) || !flag) {
            throw new BusinessException("原始密码错误，请重新输入！");
        }
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setUpdateTime(LocalDateTime.now());
        return this.baseMapper.updateById(user);
    }

    @Transactional
    @Override
    public boolean save(SysUser user) {
        Integer count = this.baseMapper.selectCount(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, user.getUsername()));
        if (Objects.nonNull(count) && count > 0) {
            throw new BusinessException("用户名称已存在！");
        }
        user.setDelFlag(Constants.NOT_DEL_FALG);
        user.setCreateTime(LocalDateTime.now());
        // 密码加密
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        int row = this.baseMapper.insert(user);
        if (row > 0) {
            if (Objects.isNull(user.getOrgId())) {
                user.setOrgId(1);
            }
            return sysUserOrgService.save(SysUserOrg.builder().userId(user.getId()).orgId(user.getOrgId()).build());
        }
        return false;
    }

    @Transactional
    @Override
    public boolean update(SysUser user) {
        user.setUpdateTime(LocalDateTime.now());
        int row = this.baseMapper.updateById(user);
        if (row > 0) {
            sysUserOrgService.remove(Wrappers.<SysUserOrg>lambdaQuery().eq(SysUserOrg::getUserId, user.getId()));
            return sysUserOrgService.save(SysUserOrg.builder().userId(user.getId()).orgId(user.getOrgId()).build());
        }
        return false;
    }

    @Transactional
    @Override
    public boolean del(SysUserDto dto) {
        int row = this.baseMapper.deleteById(dto.getId());
        if (row > 0) {
            return sysUserOrgService.remove(Wrappers.<SysUserOrg>lambdaQuery().eq(SysUserOrg::getUserId, dto.getId()));
        }
        return false;
    }

}
