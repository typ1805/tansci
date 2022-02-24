package com.tansci.domain.message.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "消息模板DTO")
public class MessageDto implements Serializable {

    @ApiModelProperty(value = "邮件主题")
    private String subject;

    @ApiModelProperty(value = "接收者：可以有多个接收者，中间用逗号隔开")
    private String recipient;

    @ApiModelProperty(value = "抄送人：可以有多个抄送人，中间用逗号隔开")
    private String cc;

    @ApiModelProperty(value = "隐秘抄送人：可以有多个抄送人，中间用逗号隔开")
    private String bcc;

    @ApiModelProperty(value = "正文")
    private String text;

    @ApiModelProperty(value = "手机号，多个以逗号隔开")
    private String phone;

    @ApiModelProperty(value = "模板编码")
    private String code;

    @ApiModelProperty(value = "模板参数")
    private TemplateParamDto param;

    @ApiModelProperty(value = "附件、图片")
    private List<File> attachments;

}
