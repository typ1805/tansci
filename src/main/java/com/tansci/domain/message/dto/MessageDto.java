package com.tansci.domain.message.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName： MailMessageDto.java
 * @ClassPath： com.tansci.domain.message.dto.MailMessageDto.java
 * @Description： 邮件消息
 * @Author： tanyp
 * @Date： 2021/6/7 9:20
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDto implements Serializable {

    // 邮件主题
    private String subject;

    // 接收者：可以有多个接收者，中间用逗号隔开
    private String recipient;

    // 抄送人：可以有多个抄送人，中间用逗号隔开
    private String cc;

    // 隐秘抄送人：可以有多个抄送人，中间用逗号隔开
    private String bcc;

    // 正文
    private String text;

    // 手机号，多个以逗号隔开
    private String phone;

    // 模板编码
    private String code;

    // 模板参数
    private TemplateParamDto param;

    // 附件、图片
    private List<File> attachments;

}
