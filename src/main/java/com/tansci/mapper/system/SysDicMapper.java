package com.tansci.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.domain.system.SysDic;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @path：com.tansci.mapper.SysDicMapper.java
 * @className：SysDicMapper.java
 * @description：字典
 * @author：tanyp
 * @dateTime：2021/7/4 13:38
 * @editNote：
 */
@Mapper
public interface SysDicMapper extends BaseMapper<SysDic> {

    List<SysDic> getDicChildrens(Integer id);

}
