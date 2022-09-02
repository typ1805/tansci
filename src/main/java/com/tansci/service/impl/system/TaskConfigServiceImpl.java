package com.tansci.service.impl.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tansci.common.constant.Enums;
import com.tansci.common.task.ScheduledTask;
import com.tansci.domain.system.TaskConfig;
import com.tansci.domain.system.dto.TaskConfigDto;
import com.tansci.mapper.system.TaskConfigMapper;
import com.tansci.service.system.TaskConfigService;
import com.tansci.utils.SecurityUserUtils;
import com.tansci.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;

/**
 * @ClassName： TaskConfigServiceImpl.java
 * @ClassPath： com.tansci.service.impl.system.TaskConfigServiceImpl.java
 * @Description： 任务配置
 * @Author： tanyp
 * @Date： 2022/2/25 9:46
 **/
@Slf4j
@Service
public class TaskConfigServiceImpl extends ServiceImpl<TaskConfigMapper, TaskConfig> implements TaskConfigService {

    @Autowired
    private ScheduledTask scheduledTask;

    @Override
    public IPage<TaskConfig> page(Page page, TaskConfigDto dto) {
        IPage<TaskConfig> iPage = this.baseMapper.selectPage(page,
                Wrappers.<TaskConfig>lambdaQuery()
                        .eq(Objects.nonNull(dto.getStatus()), TaskConfig::getStatus, dto.getStatus())
                        .eq(TaskConfig::getCreater, SecurityUserUtils.getUser().getUsername())
                        .like(Objects.nonNull(dto.getName()), TaskConfig::getName, dto.getName())
                        .orderByDesc(TaskConfig::getUpdateTime)
        );
        iPage.getRecords().forEach(item -> {
            item.setStatusName(Enums.getVlaueByGroup(item.getStatus(), "status"));
        });
        return iPage;
    }

    @Override
    public boolean save(TaskConfig taskConfig) {
        taskConfig.setStatus(0);
        taskConfig.setUpdateTime(LocalDateTime.now());
        taskConfig.setCreateTime(LocalDateTime.now());
        taskConfig.setCreater(SecurityUserUtils.getUser().getUsername());
        taskConfig.setTaskId("T" + UUIDUtils.getUUID(20).toUpperCase(Locale.ROOT));
        int row = this.baseMapper.insert(taskConfig);
        if (row > 0) {
            scheduledTask.refreshTask(this.list(Wrappers.<TaskConfig>lambdaQuery().eq(TaskConfig::getStatus, 1)));
            return true;
        }
        return true;
    }

    @Override
    public boolean update(TaskConfig taskConfig) {
        taskConfig.setUpdateTime(LocalDateTime.now());
        int row = this.baseMapper.updateById(taskConfig);
        if (row > 0) {
            scheduledTask.refreshTask(this.list(Wrappers.<TaskConfig>lambdaQuery().eq(TaskConfig::getStatus, 1)));
            return true;
        }
        return false;
    }

    @Override
    public boolean del(TaskConfigDto dto) {
        int row = this.baseMapper.deleteById(dto.getId());
        if (row > 0) {
            scheduledTask.refreshTask(this.list(Wrappers.<TaskConfig>lambdaQuery().eq(TaskConfig::getStatus, 1)));
            return true;
        }
        return false;
    }

}
