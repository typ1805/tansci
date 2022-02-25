package com.tansci.service.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tansci.domain.system.TaskConfig;
import com.tansci.domain.system.dto.TaskConfigDto;

/**
 * @ClassName： TaskConfigService.java
 * @ClassPath： com.tansci.service.system.TaskConfigService.java
 * @Description： 任务配置
 * @Author： tanyp
 * @Date： 2022/2/25 9:45
 **/
public interface TaskConfigService extends IService<TaskConfig> {

    IPage<TaskConfig> page(Page page, TaskConfigDto dto);

    boolean save(TaskConfig taskConfig);

    boolean update(TaskConfig taskConfig);

    boolean del(TaskConfigDto dto);

}
