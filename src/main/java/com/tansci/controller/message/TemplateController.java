package com.tansci.controller.message;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.common.annotation.Log;
import com.tansci.common.constant.Constants;
import com.tansci.domain.message.Template;
import com.tansci.domain.message.TemplateDetails;
import com.tansci.domain.message.dto.TemplateDto;
import com.tansci.service.message.TemplateDetailsService;
import com.tansci.service.message.TemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName： TemplateController
 * @ClassPath： com.tansci.controller.message.TemplateController.java
 * @Description： 模板配置
 * @Author： tanyp
 * @Date： 2021/4/22 10:39
 **/
@RestController
@AllArgsConstructor
@RequestMapping("/template")
@Api(value = "template", tags = "模板配置")
public class TemplateController {

    @Autowired
    private TemplateService templateService;
    @Autowired
    private TemplateDetailsService templateDetailsService;

    @ApiOperation(value = "模板列表", notes = "模板列表")
    @Log(modul = "模板配置-模板列表", type = Constants.SELECT, desc = "模板列表")
    @GetMapping("/page")
    public Wrapper<IPage<Template>> page(Page page, TemplateDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, templateService.page(page, dto));
    }

    @ApiOperation(value = "消息发送日志", notes = "消息发送日志")
    @Log(modul = "模板配置-消息发送日志", type = Constants.SELECT, desc = "消息发送日志")
    @GetMapping("/logPage")
    public Wrapper<IPage<TemplateDetails>> logPage(Page page, TemplateDetails details) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, templateDetailsService.page(page, details));
    }

    @ApiOperation(value = "添加模板", notes = "添加模板")
    @Log(modul = "模板配置-添加模板", type = Constants.INSERT, desc = "添加模板")
    @PostMapping("/save")
    public Wrapper save(@RequestBody Template template) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, templateService.saveTemplate(template));
    }

    @ApiOperation(value = "删除模板", notes = "删除模板")
    @Log(modul = "模板配置-删除模板", type = Constants.DELETE, desc = "删除模板")
    @GetMapping("/delete")
    public Wrapper delete(@RequestParam String id) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, templateService.delTemplate(id));
    }

    @ApiOperation(value = "根据id修改模板", notes = "根据id修改模板")
    @Log(modul = "模板配置-根据id修改模板", type = Constants.UPDATE, desc = "根据id修改模板")
    @PostMapping("/update")
    public Wrapper update(@RequestBody Template template) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, templateService.updateTemplate(template));
    }

}
