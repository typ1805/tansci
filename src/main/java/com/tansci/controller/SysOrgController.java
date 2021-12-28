package com.tansci.controller;

import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.common.annotation.Log;
import com.tansci.common.constant.Constants;
import com.tansci.domain.SysOrg;
import com.tansci.service.SysOrgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @path：com.tanersci.controller.SysOrgController.java
 * @className：SysOrgController.java
 * @description：组织
 * @author：tanyp
 * @dateTime：2021/10/23 13:46
 * @editNote：
 */
@Slf4j
@RestController
@RequestMapping("/org")
public class SysOrgController {

    @Autowired
    private SysOrgService sysOrgService;

    @Log(modul = "组织-列表", type = Constants.SELECT, desc = "列表")
    @GetMapping("/list")
    public Wrapper<List<SysOrg>> list(SysOrg sysOrg) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysOrgService.list(sysOrg));
    }

    @Log(modul = "组织-添加组织信息", type = Constants.INSERT, desc = "添加组织信息")
    @PostMapping("/save")
    public Wrapper<Boolean> save(@RequestBody SysOrg sysOrg) {
        sysOrg.setCreateTime(LocalDateTime.now());
        sysOrg.setDelFlag(0);
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysOrgService.save(sysOrg));
    }

    @Log(modul = "组织-修改组织信息", type = Constants.UPDATE, desc = "修改组织信息")
    @PostMapping("/update")
    public Wrapper<Boolean> update(@RequestBody SysOrg sysOrg) {
        sysOrg.setUpdateTime(LocalDateTime.now());
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysOrgService.updateById(sysOrg));
    }

    @Log(modul = "组织-删除组织信息", type = Constants.SELECT, desc = "删除组织信息")
    @GetMapping("/del")
    public Wrapper<Boolean> del(Integer id) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, sysOrgService.del(id));
    }

}
