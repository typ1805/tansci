package com.tansci.service.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.system.TaskLog;

/**
 * @ClassName： TaskLogService.java
 * @ClassPath： com.tansci.service.system.TaskLogService.java
 * @Description： 调度执行日志
 * @Author： tanyp
 * @Date： 2022/9/2 15:29
 **/
public interface TaskLogService extends IService<TaskLog> {

    IPage<TaskLog> page(Page page, TaskLog log);

}
