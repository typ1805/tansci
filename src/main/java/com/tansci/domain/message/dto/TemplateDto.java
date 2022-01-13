package com.tansci.domain.message.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName： TemplateDto.java
 * @ClassPath： com.tansci.domain.message.dto.TemplateDto.java
 * @Description： 模板配置请求实体
 * @Author： tanyp
 * @Date： 2021/4/22 10:44
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TemplateDto implements Serializable {

    // 模板类型：0：验证码，1：短信通知，2：推广短信，3：国际/港澳台消息
    private Integer templateType;

    // 模板名称
    private String templateName;

    // 业务类型：0：短信，1：邮件
    private Integer businessType;

}
