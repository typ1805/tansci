package com.tansci.service.message;

import com.tansci.domain.message.dto.MessageDto;
import com.tansci.domain.message.vo.MessageVo;

/**
 * @ClassName： MailService.java
 * @ClassPath： com.tansci.service.message.MailService.java
 * @Description： 邮件
 * @Author： tanyp
 * @Date： 2021/6/7 9:18
 **/
public interface MailService {

    MessageVo sendSimple(MessageDto dto);

    MessageVo sendAttachFile(MessageDto dto);

    MessageVo sendImgRes(MessageDto dto);

}
