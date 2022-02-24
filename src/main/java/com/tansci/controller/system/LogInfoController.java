package com.tansci.controller.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.domain.system.LogErrorInfo;
import com.tansci.domain.system.LogInfo;
import com.tansci.service.system.LogErrorInfoService;
import com.tansci.service.system.LogInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @path：com.tansci.controller.LogInfoController.java
 * @className：LogInfoController.java
 * @description：操作日志
 * @author：tanyp
 * @dateTime：2021/11/19 9:34
 * @editNote：
 */
@Slf4j
@RestController
@RequestMapping("/log")
@Api(value = "log", tags = "操作日志")
public class LogInfoController {

    @Autowired
    private LogInfoService logInfoService;

    @Autowired
    private LogErrorInfoService logErrorInfoService;

    //    @Log(modul = "日志-操作日志分页", type = Constants.SELECT, desc = "操作日志分页")
    @ApiOperation(value = "操作日志分页", notes = "操作日志分页")
    @GetMapping("/logInfoPage")
    public Wrapper<IPage<LogInfo>> logInfoPage(Page page) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, logInfoService.page(page, Wrappers.<LogInfo>lambdaQuery().orderByDesc(LogInfo::getCreateTime)));
    }

    //    @Log(modul = "日志-异常日志分页", type = Constants.SELECT, desc = "异常日志分页")
    @ApiOperation(value = "异常日志分页", notes = "异常日志分页")
    @GetMapping("/logErrorPage")
    public Wrapper<IPage<LogErrorInfo>> logErrorPage(Page page) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, logErrorInfoService.page(page, Wrappers.<LogErrorInfo>lambdaQuery().orderByDesc(LogErrorInfo::getCreateTime)));
    }

}
