package com.tansci.service.impl.system;

import com.tansci.service.system.TaskContextService;
import com.tansci.service.system.TaskRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        componentServices.get(taskServerName).register();
    }

}
