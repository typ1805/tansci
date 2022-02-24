package com.tansci.controller.system;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.common.annotation.Log;
import com.tansci.common.constant.Constants;
import com.tansci.domain.system.SysDic;
import com.tansci.domain.system.dto.SysDicDto;
import com.tansci.service.system.SysDicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @path：com.tansci.controller.SysDicController.java
 * @className：SysDicController.java
 * @description：字典
 * @author：tanyp
 * @dateTime：2021/7/4 14:03
 * @editNote：
 */
@Slf4j
@RestController
@RequestMapping("/dic")
@Api(value = "dic", tags = "数据字典")
public class SysDicController {

    @Autowired
    private SysDicService sysDicService;

    @ApiOperation(value = "列表", notes = "列表")
    @Log(modul = "数据字典-列表", type = Constants.SELECT, desc = "列表")
    @GetMapping("/dicList")
    public Wrapper<List<SysDic>> dicList(SysDicDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysDicService.dicList(dto));
    }

    @ApiOperation(value = "根据分组名称获取列表", notes = "根据分组名称获取列表")
    @Log(modul = "数据字典-根据分组名称获取列表", type = Constants.SELECT, desc = "根据分组名称获取列表")
    @GetMapping("/getGroupNameByList")
    public Wrapper<List<SysDic>> getGroupNameByList(SysDicDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE,
                sysDicService.list(
                        Wrappers.<SysDic>lambdaQuery()
                                .ne(SysDic::getDicValue, -1)
                                .eq(Objects.nonNull(dto.getParentId()), SysDic::getParentId, dto.getParentId())
                                .eq(Objects.nonNull(dto.getGroupName()), SysDic::getGroupName, dto.getGroupName())
                                .eq(Objects.nonNull(dto.getType()), SysDic::getType, dto.getType())
                                .between(Objects.nonNull(dto.getStartTime()) && Objects.nonNull(dto.getEndTime()), SysDic::getCreateTime, dto.getStartTime(), dto.getEndTime())
                                .orderByAsc(SysDic::getSort)
                )
        );
    }

    @ApiOperation(value = "添加字典", notes = "添加字典")
    @Log(modul = "数据字典-添加字典", type = Constants.SELECT, desc = "添加字典")
    @PostMapping("/save")
    public Wrapper<Boolean> save(@RequestBody SysDic sysDic) {
        sysDic.setCreateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysDicService.save(sysDic));
    }

    @ApiOperation(value = "更新字典", notes = "更新字典")
    @Log(modul = "数据字典-更新字典", type = Constants.UPDATE, desc = "更新字典")
    @PostMapping("/update")
    public Wrapper<Boolean> update(@RequestBody SysDic sysDic) {
        sysDic.setUpdateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysDicService.updateById(sysDic));
    }

    @ApiOperation(value = "删除", notes = "删除")
    @Log(modul = "数据字典-删除", type = Constants.DELETE, desc = "删除字段信息")
    @GetMapping("/del")
    public Wrapper<Boolean> del(SysDicDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysDicService.del(dto.getId()));
    }

}
