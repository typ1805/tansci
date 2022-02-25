package com.tansci.service.system;

/**
 * @ClassName： TaskContextService.java
 * @ClassPath： com.tansci.service.system.TaskContextService.java
 * @Description： 动态调用任务配置信息
 * @Author： tanyp
 * @Date： 2022/2/25 10:09
 **/
public interface TaskContextService {

    void execute(String taskServerName);

}
