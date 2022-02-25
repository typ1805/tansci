package com.tansci.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.common.annotation.Log;
import com.tansci.common.constant.Constants;
import com.tansci.domain.system.TaskConfig;
import com.tansci.domain.system.dto.TaskConfigDto;
import com.tansci.service.system.TaskConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName： TaskConfigController.java
 * @ClassPath： com.tansci.controller.system.TaskConfigController.java
 * @Description： 任务配置
 * @Author： tanyp
 * @Date： 2022/2/25 10:46
 **/
@Slf4j
@RestController
@RequestMapping("/taskConfig")
@Api(value = "taskConfig", tags = "任务配置")
public class TaskConfigController {

    @Autowired
    private TaskConfigService taskConfigService;

    @ApiOperation(value = "任务分页", notes = "任务分页")
    @Log(modul = "任务配置-任务分页", type = Constants.SELECT, desc = "任务分页")
    @GetMapping("/page")
    public Wrapper<IPage<TaskConfig>> page(Page page, TaskConfigDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, taskConfigService.page(page, dto));
    }

    @ApiOperation(value = "添加任务", notes = "添加任务")
    @Log(modul = "任务配置-添加任务", type = Constants.INSERT, desc = "添加任务")
    @PostMapping("/save")
    public Wrapper<Boolean> save(@RequestBody TaskConfig taskConfig) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, taskConfigService.save(taskConfig));
    }

    @ApiOperation(value = "修改任务", notes = "修改任务")
    @Log(modul = "任务配置-修改任务", type = Constants.UPDATE, desc = "修改任务")
    @PostMapping("/update")
    public Wrapper<Boolean> update(@RequestBody TaskConfig taskConfig) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, taskConfigService.update(taskConfig));
    }

    @ApiOperation(value = "删除任务", notes = "删除任务")
    @Log(modul = "任务配置-删除任务", type = Constants.DELETE, desc = "删除任务")
    @GetMapping("/del")
    public Wrapper<Boolean> del(TaskConfigDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, taskConfigService.del(dto));
    }

}
