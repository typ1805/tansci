package com.tansci.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.domain.system.SysOrg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @path：com.tansci.mapper.SysOrgMapper.java
 * @className：SysOrgMapper.java
 * @description：组织
 * @author：tanyp
 * @dateTime：2021/10/23 13:41
 * @editNote：
 */
@Mapper
public interface SysOrgMapper extends BaseMapper<SysOrg> {

    List<SysOrg> getOrgChildrens(Integer id);

}
