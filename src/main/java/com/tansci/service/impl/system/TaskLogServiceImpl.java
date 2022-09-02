package com.tansci.service.impl.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.common.constant.Enums;
import com.tansci.domain.system.TaskLog;
import com.tansci.mapper.system.TaskLogMapper;
import com.tansci.service.system.TaskLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @ClassName： TaskLogServiceImpl.java
 * @ClassPath： com.tansci.service.impl.system.TaskLogServiceImpl.java
 * @Description： 调度执行日志
 * @Author： tanyp
 * @Date： 2022/9/2 15:29
 **/
@Slf4j
@Service
public class TaskLogServiceImpl extends ServiceImpl<TaskLogMapper, TaskLog> implements TaskLogService {

    @Override
    public IPage<TaskLog> page(Page page, TaskLog log) {
        IPage<TaskLog> iPage = this.baseMapper.selectPage(page,
                Wrappers.lambdaQuery()
        );

        if (Objects.nonNull(iPage.getRecords()) && iPage.getRecords().size() > 0) {
            iPage.getRecords().forEach(item -> {
                item.setStatusName(Objects.nonNull(item.getStatus()) ? Enums.getVlaueByGroup(item.getStatus(), "task_log_status") : null);
            });
        }
        return iPage;
    }

}
