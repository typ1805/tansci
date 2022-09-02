package com.tansci.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tansci.domain.system.TaskLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName： TaskLogMapper.java
 * @ClassPath： com.tansci.mapper.system.TaskLogMapper.java
 * @Description： 调度执行日志
 * @Author： tanyp
 * @Date： 2022/9/2 15:28
 **/
@Mapper
public interface TaskLogMapper extends BaseMapper<TaskLog> {
}
