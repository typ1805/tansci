package com.tansci.service.impl.system;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.domain.system.SysOrg;
import com.tansci.domain.system.SysOrgRole;
import com.tansci.mapper.system.SysOrgMapper;
import com.tansci.service.system.SysOrgRoleService;
import com.tansci.service.system.SysOrgService;
import com.tansci.utils.SecurityUserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName： SysOrgServiceImpl.java
 * @ClassPath： com.tansci.service.impl.SysOrgServiceImpl.java
 * @Description： 组织机构
 * @Author： tanyp
 * @Date： 2021/10/22 16:29
 **/
@Slf4j
@Service
public class SysOrgServiceImpl extends ServiceImpl<SysOrgMapper, SysOrg> implements SysOrgService {

    @Autowired
    private SysOrgRoleService sysOrgRoleService;

    @Override
    public List<SysOrg> list(SysOrg sysOrg) {
        LambdaQueryWrapper queryWrapper = null;
        if (Objects.equals(0, SecurityUserUtils.getUser().getType())) {
            queryWrapper = Wrappers.<SysOrg>lambdaQuery()
                    .eq(SysOrg::getDelFlag, 0)
                    .like(Objects.nonNull(sysOrg.getName()), SysOrg::getName, sysOrg.getName())
                    .orderByDesc(SysOrg::getCreateTime);
        } else {
            queryWrapper = Wrappers.<SysOrg>lambdaQuery()
                    .eq(SysOrg::getDelFlag, 0)
                    .in(SysOrg::getId, SecurityUserUtils.getUser().getOrgIds())
                    .like(Objects.nonNull(sysOrg.getName()), SysOrg::getName, sysOrg.getName())
                    .orderByDesc(SysOrg::getCreateTime);

        }
        List<SysOrg> orgList = this.baseMapper.selectList(queryWrapper);

        List<SysOrg> newOrgList = orgList.stream().filter(item -> item.getParentId() == 0).map(item -> {
            item.setChildren(this.getChildrens(item, orgList));
            return item;
        }).sorted((item1, item2) -> {
            return (item1.getSort() == null ? 0 : item1.getSort()) - (item2.getSort() == null ? 0 : item2.getSort());
        }).collect(Collectors.toList());
        return newOrgList.size() > 0 ? newOrgList : orgList;
    }

    /**
     * @MonthName： getChildrens
     * @Description： 封装树形数据
     * @Author： tanyp
     * @Date： 2021/7/6 16:10
     * @Param： [dic, list]
     * @return： java.util.List<com.kuiper.qms.domain.SysOrg>
     **/
    public List<SysOrg> getChildrens(SysOrg org, List<SysOrg> list) {
        List<SysOrg> treeOrg = list.stream().filter(item -> Objects.equals(item.getParentId(), org.getId())).map(item -> {
            // 递归添加子数据
            List<SysOrg> childrens = getChildrens(item, list);
            item.setChildren(childrens);
            return item;
        }).sorted((item1, item2) -> { // 排序
            return (item1.getSort() == null ? 0 : item1.getSort()) - (item2.getSort() == null ? 0 : item2.getSort());
        }).collect(Collectors.toList());
        return treeOrg;
    }

    @Transactional
    @Override
    public boolean del(Integer id) {
        List<Integer> ids = new ArrayList<>();
        ids.add(id);
        List<SysOrg> orgList = this.baseMapper.getOrgChildrens(id);
        if (Objects.nonNull(orgList) && orgList.size() > 0) {
            ids.addAll(orgList.stream().map(SysOrg::getId).collect(Collectors.toList()));
        }
        this.baseMapper.deleteBatchIds(ids);
        return sysOrgRoleService.remove(Wrappers.<SysOrgRole>lambdaQuery().in(SysOrgRole::getOrgId, ids));
    }

    @Override
    public List<SysOrg> getOrgChildrens(Integer id) {
        return this.baseMapper.getOrgChildrens(id);
    }
}
