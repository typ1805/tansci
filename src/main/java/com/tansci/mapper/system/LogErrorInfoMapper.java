package com.tansci.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.domain.system.LogErrorInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @path：com.tansci.mapper.LogErrorInfoMapper.java
 * @className：LogErrorInfoMapper.java
 * @description：操作日志异常信息
 * @author：tanyp
 * @dateTime：2021/11/18 13:47
 * @editNote：
 */
@Mapper
public interface LogErrorInfoMapper extends BaseMapper<LogErrorInfo> {
}
