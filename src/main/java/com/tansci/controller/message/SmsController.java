package com.tansci.controller.message;

import com.tansci.common.WrapMapper;
import com.tansci.common.Wrapper;
import com.tansci.common.annotation.Log;
import com.tansci.common.constant.Constants;
import com.tansci.domain.message.dto.MessageDto;
import com.tansci.domain.message.dto.SmsTemplateDto;
import com.tansci.domain.message.vo.MessageVo;
import com.tansci.service.message.SmsService;
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
public class SmsController {

    @Autowired
    @Qualifier("aliSmsServiceImpl")
    private SmsService aliSmsServiceImpl;

    @Log(modul = "短信-短信发送", type = Constants.INSERT, desc = "短信发送")
    @PostMapping("/send")
    public Wrapper<MessageVo> send(@RequestBody MessageDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, aliSmsServiceImpl.send(dto));
    }

    @Log(modul = "短信-申请短信模板", type = Constants.INSERT, desc = "申请短信模板")
    @PostMapping("/addSmsTemplate")
    public Wrapper<MessageVo> addSmsTemplate(@RequestBody SmsTemplateDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, aliSmsServiceImpl.addSmsTemplate(dto));
    }

    @Log(modul = "短信-删除短信模板", type = Constants.DELETE, desc = "删除短信模板")
    @PostMapping("/deleteSmsTemplate")
    public Wrapper<MessageVo> deleteSmsTemplate(@RequestBody SmsTemplateDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, aliSmsServiceImpl.deleteSmsTemplate(dto));
    }

    @Log(modul = "短信-修改未通过审核的短信模板", type = Constants.UPDATE, desc = "修改未通过审核的短信模板")
    @PostMapping("/modifySmsTemplate")
    public Wrapper<MessageVo> modifySmsTemplate(@RequestBody SmsTemplateDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, aliSmsServiceImpl.modifySmsTemplate(dto));
    }

    @Log(modul = "短信-查询短信模板的审核状态", type = Constants.SELECT, desc = "查询短信模板的审核状态")
    @PostMapping("/querySmsTemplate")
    public Wrapper<MessageVo> querySmsTemplate(@RequestBody SmsTemplateDto dto) {
        return WrapMapper.wrap(Wrapper.SUCCESS_CODE, Wrapper.SUCCESS_MESSAGE, aliSmsServiceImpl.querySmsTemplate(dto));
    }

}
