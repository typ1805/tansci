package com.tansci.service.impl.system.task;

import com.tansci.service.system.TaskRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName： TaskTest1ServiceImpl.java
 * @ClassPath： com.tansci.service.impl.system.task.TaskTest1ServiceImpl.java
 * @Description： 自定义任务测试1
 * @Author： tanyp
 * @Date： 2022/2/25 10:08
 **/
@Slf4j
@Service("taskTest1Service")
public class TaskTest1ServiceImpl implements TaskRegisterService {

    @Override
    public void register() {
        log.info("===========自定义任务测试【TaskTest1ServiceImpl】====【1】=========");
    }
}
