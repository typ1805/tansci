package com.tansci.service.message;

import com.tansci.domain.message.dto.MessageDto;
import com.tansci.domain.message.dto.SmsTemplateDto;
import com.tansci.domain.message.vo.MessageVo;

/**
 * @ClassName： SmsService.java
 * @ClassPath： com.tansci.service.message.SmsService.java
 * @Description： 短信
 * @Author： tanyp
 * @Date： 2021/6/7 10:56
 **/
public interface SmsService {

    MessageVo send(MessageDto dto);

    MessageVo addSmsTemplate(SmsTemplateDto template);

    MessageVo deleteSmsTemplate(SmsTemplateDto template);

    MessageVo modifySmsTemplate(SmsTemplateDto template);

    MessageVo querySmsTemplate(SmsTemplateDto template);

}
