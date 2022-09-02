package com.tansci.service.impl.system;

import com.tansci.domain.system.TaskLog;
import com.tansci.service.system.TaskContextService;
import com.tansci.service.system.TaskLogService;
import com.tansci.service.system.TaskRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @ClassName： TaskContextServiceImpl.java
 * @ClassPath： com.tansci.service.impl.system.TaskContextServiceImpl.java
 * @Description： 动态调用任务配置信息
 * @Author： tanyp
 * @Date： 2022/2/25 10:12
 **/
@Slf4j
@Service
public class TaskContextServiceImpl implements TaskContextService {

    /**
     * 任务注册器
     */
    @Autowired
    private Map<String, TaskRegisterService> componentServices;
    @Autowired
    private TaskLogService taskLogService;

    /**
     * @MonthName： execute
     * @Description： 解析器
     * @Author： tanyp
     * @Date： 2022/2/25 10:13
     * @Param： [taskServerName]
     * @return： void
     **/
    @Override
    public void execute(String taskServerName) {
        TaskLog taskLog = TaskLog.builder().serverName(taskServerName).executionTime(LocalDateTime.now()).createTime(LocalDateTime.now()).remarks("执行成功").status(0).build();
        try {
            componentServices.get(taskServerName).register();
        } catch (Exception e) {
            log.error("===执行任务调度异常，任务服务名称：{}，异常信息：{}", taskServerName, e);
            taskLog.setStatus(1);
            taskLog.setRemarks("任务服务【" + taskServerName + "】执行失败");
        }
        taskLogService.save(taskLog);
    }

}
