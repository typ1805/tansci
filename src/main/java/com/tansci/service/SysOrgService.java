package com.tansci.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.SysOrg;

import java.util.List;

/**
 * @path：com.tansci.service.SysOrgService.java
 * @className：SysOrgService.java
 * @description：组织
 * @author：tanyp
 * @dateTime：2021/10/23 13:42
 * @editNote：
 */
public interface SysOrgService extends IService<SysOrg> {

    IPage<SysOrg> page(Page page, SysOrg sysOrg);

    List<SysOrg> list(SysOrg sysOrg);

    boolean del(Integer id);

    List<SysOrg> getOrgChildrens(Integer id);
}
