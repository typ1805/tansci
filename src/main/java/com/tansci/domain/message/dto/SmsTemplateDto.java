package com.tansci.domain.message.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName： SmsTemplate.java
 * @ClassPath： com.tansci.domain.message.dto.SmsTemplateDto.java
 * @Description： 短信模板
 * @Author： tanyp
 * @Date： 2021/6/7 15:02
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "短信模板DTO")
public class SmsTemplateDto implements Serializable {

    @ApiModelProperty(value = "模板类型：0：验证码，1：短信通知，2：推广短信，3：国际/港澳台消息。")
    private Integer templateType;

    @ApiModelProperty(value = "模板名称，长度为1~30个字符")
    private String templateName;

    @ApiModelProperty(value = "模板内容，长度为1~500个字符")
    private String templateContent;

    @ApiModelProperty(value = "短信模板CODE")
    private String templateCode;

    @ApiModelProperty(value = "短信模板申请说明。请在申请说明中描述您的业务使用场景，长度为1~100个字符")
    private String remark;

}
