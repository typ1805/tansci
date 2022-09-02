package com.tansci.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.common.annotation.Log;
import com.tansci.common.constant.Constants;
import com.tansci.domain.system.TaskLog;
import com.tansci.service.system.TaskLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName： TaskLogController.java
 * @ClassPath： com.tansci.controller.system.TaskLogController.java
 * @Description： 调度执行日志
 * @Author： tanyp
 * @Date： 2022/9/2 15:30
 **/
@Slf4j
@RestController
@RequestMapping("/taskLog")
@Api(value = "taskLog", tags = "调度执行日志")
public class TaskLogController {

    @Autowired
    private TaskLogService taskLogService;

    @ApiOperation(value = "分页", notes = "分页")
    @Log(modul = "调度执行日志-分页", type = Constants.SELECT, desc = "分页")
    @GetMapping("/page")
    public Wrapper<IPage<TaskLog>> page(Page page, TaskLog log) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, taskLogService.page(page, log));
    }

    @ApiOperation(value = "清空日志", notes = "清空日志")
    @Log(modul = "调度执行日志-清空日志", type = Constants.SELECT, desc = "清空日志")
    @GetMapping("/clear")
    public Wrapper<Object> clear() {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, taskLogService.remove(Wrappers.lambdaQuery()));
    }

}
