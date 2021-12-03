package com.tansci.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.common.constant.Constants;
import com.tansci.common.exception.BusinessException;
import com.tansci.domain.SysUser;
import com.tansci.domain.dto.SysUserDto;
import com.tansci.mapper.SysUserMapper;
import com.tansci.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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

    private PasswordEncoder passwordEncoder;

    @Override
    public IPage<SysUser> page(Page page, SysUserDto dto) {

        return this.baseMapper.selectPage(page, Wrappers.lambdaQuery());
    }

    @Override
    public List<SysUser> list(SysUserDto dto) {
        return this.baseMapper.selectList(Wrappers.lambdaQuery());
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
        user.setDelFlag(Constants.NOT_DEL_FALG);
        user.setCreateTime(LocalDateTime.now());
        // 密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        int row = this.baseMapper.insert(user);
        if (row > 0) {
//            return sysOrgUserService.save(SysOrgUser.builder().userId(user.getId()).orgId(user.getOrgId()).build());
        }
        return false;
    }

    @Transactional
    @Override
    public boolean update(SysUser user) {
        user.setUpdateTime(LocalDateTime.now());
        int row = this.baseMapper.updateById(user);
        if (row > 0) {
//            sysOrgUserService.remove(Wrappers.<SysOrgUser>lambdaQuery().eq(SysOrgUser::getUserId, user.getId()));
//            return sysOrgUserService.save(SysOrgUser.builder().userId(user.getId()).orgId(user.getOrgId()).build());
        }
        return false;
    }

    @Transactional
    @Override
    public boolean del(SysUserDto dto) {
        int row = this.baseMapper.deleteById(dto.getId());
        if (row > 0) {
//            return sysOrgUserService.remove(Wrappers.<SysOrgUser>lambdaQuery().eq(SysOrgUser::getUserId, dto.getId()));
        }
        return false;
    }

}
