package com.tansci.controller.message;

import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.common.annotation.Log;
import com.tansci.common.constant.Constants;
import com.tansci.domain.message.dto.MessageDto;
import com.tansci.domain.message.dto.SmsTemplateDto;
import com.tansci.domain.message.vo.MessageVo;
import com.tansci.service.message.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName： SmsController.java
 * @ClassPath： com.tansci.controller.message.SmsController.java
 * @Description： 短信
 * @Author： tanyp
 * @Date： 2021/6/7 10:56
 **/
@Slf4j
@RestController
@RequestMapping("/sms")
@Api(value = "sms", tags = "短信模板")
public class SmsController {

    @Autowired
    @Qualifier("aliSmsServiceImpl")
    private SmsService aliSmsServiceImpl;

    @ApiOperation(value = "发送短信", notes = "发送短信")
    @Log(modul = "短信-发送短信", type = Constants.INSERT, desc = "发送短信")
    @PostMapping("/send")
    public Wrapper<MessageVo> send(@RequestBody MessageDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, aliSmsServiceImpl.send(dto));
    }

    @ApiOperation(value = "申请短信模板", notes = "申请短信模板")
    @Log(modul = "短信-申请短信模板", type = Constants.INSERT, desc = "申请短信模板")
    @PostMapping("/addSmsTemplate")
    public Wrapper<MessageVo> addSmsTemplate(@RequestBody SmsTemplateDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, aliSmsServiceImpl.addSmsTemplate(dto));
    }

    @ApiOperation(value = "删除短信模板", notes = "删除短信模板")
    @Log(modul = "短信-删除短信模板", type = Constants.DELETE, desc = "删除短信模板")
    @PostMapping("/deleteSmsTemplate")
    public Wrapper<MessageVo> deleteSmsTemplate(@RequestBody SmsTemplateDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, aliSmsServiceImpl.deleteSmsTemplate(dto));
    }

    @ApiOperation(value = "修改未通过审核的短信模板", notes = "修改未通过审核的短信模板")
    @Log(modul = "短信-修改未通过审核的短信模板", type = Constants.UPDATE, desc = "修改未通过审核的短信模板")
    @PostMapping("/modifySmsTemplate")
    public Wrapper<MessageVo> modifySmsTemplate(@RequestBody SmsTemplateDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, aliSmsServiceImpl.modifySmsTemplate(dto));
    }

    @ApiOperation(value = "查询短信模板的审核状态", notes = "查询短信模板的审核状态")
    @Log(modul = "短信-查询短信模板的审核状态", type = Constants.SELECT, desc = "查询短信模板的审核状态")
    @PostMapping("/querySmsTemplate")
    public Wrapper<MessageVo> querySmsTemplate(@RequestBody SmsTemplateDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, aliSmsServiceImpl.querySmsTemplate(dto));
    }

}
